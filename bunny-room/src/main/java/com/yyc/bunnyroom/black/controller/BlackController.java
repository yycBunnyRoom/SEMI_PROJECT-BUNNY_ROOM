package com.yyc.bunnyroom.black.controller;

import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import com.yyc.bunnyroom.black.service.BlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("admin/black")
public class BlackController {

    @Autowired
    private BlackService blackService;

    /**
     * 블랙리스트 관리 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping
    private String black(Model model){
        List<BlackDTO> blacklist = blackService.showAll();
        model.addAttribute("blacklist", blacklist);
        return "admin/blacklist";
    }

    /**
     * 블랙리스트를 검색하는 요청을 수행하는 메소드
     * */
    @GetMapping("/showBlacklist")
    public String showAllBlacklist(@RequestParam String str, Model model){

        if(str.isEmpty()){
            // 블랙리스트 전체 검색
            List<BlackDTO> blacklist = blackService.showAll();
            model.addAttribute("blacklist", blacklist);
            return "admin/blacklist";

        }else{
            // 조건에 따른 검색
            List<BlackDTO> blacklist = blackService.showBlacklist(str);
            model.addAttribute("blacklist", blacklist);
            return "admin/blacklist";
        }
    }

    @PostMapping("/addBlacklist")
    public String addBlackReason(@RequestParam(name = "userNo") String userNo,
                                 @RequestParam(name = "email") String email,
                                 @RequestParam(name = "nickname") String nickname,
                                 @RequestParam(name = "phone") String phone, Model model){
        // 해당회원의 정보 중 블랙리스트 정보에 들어갈 정보 뽑기
        model.addAttribute("userNo", userNo);
        model.addAttribute("email", email);
        model.addAttribute("nickname", nickname);
        model.addAttribute("phone", phone);

        return "admin/addBlackReason";
    }

    /**
     * 회원관리 페이지에서 해당 회원을 블랙리스트로 변경하고 블랙리스트 명단에 입력하는 메소드
     * */
    @PostMapping("/insertBlacklist")
    public String addBlacklist(@RequestParam(name = "userNo") int userNo,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "blackReason") String reason, Model model){
        // 블랙리스트에 등재하기
        int insert = blackService.addBlacklist(userNo, reason);

        // 블랙리스트로 회원 권한을 변경
        System.out.println("메일이 " + email + "인 회원을 블랙리스트 상태로 변경합니다.");
        int update = blackService.toBlacklist(email);

        if(insert > 0 && update > 0) {// 정상적인 블랙 처리
            model.addAttribute("blacklist", email + "회원이 블랙처리되었습니다.");
            return "admin/member";
        }else {// 비정상적인 블랙 처리
            System.out.println("정상적으로 동작되지 않았습니다.");
            model.addAttribute("blacklist", "블랙처리에 실패하였습니다.");
            return "admin/member";
        }
    }
}
