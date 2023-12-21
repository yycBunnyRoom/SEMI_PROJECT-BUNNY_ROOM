package com.yyc.bunnyroom.black.controller;

import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import com.yyc.bunnyroom.black.service.BlackService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BlackController {

    private final BlackService blackService;

    /**
     * 블랙리스트 관리 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping
    private String black(Model model){
        List<BlackDTO> blacklist = blackService.showAll();
        model.addAttribute("blacklist", blacklist);
        return "black/blacklist";
    }

    /**
     * 블랙리스트를 검색하는 요청을 수행하는 메소드
     * */
    @GetMapping("/showBlacklist")
    public String showBlacklist(@RequestParam(name = "mode")String mode, @RequestParam(name = "str") String str, Model model){
        System.out.println(mode);
        System.out.println(str);

        if(str.isEmpty() && !mode.equals("inactive")) {
            // 블랙리스트 전체 검색
            List<BlackDTO> blacklist = blackService.showAll();
            model.addAttribute("blacklist", blacklist);
        }else if(mode.equals("email")){
            // 이메일로 검색
            List<BlackDTO> blacklist = blackService.showBlacklistByEmail(str);
            model.addAttribute("blacklist", blacklist);
        }else if(mode.equals("nickname")) {
            // 닉네임으로 검색
            List<BlackDTO> blacklist = blackService.showBlacklistByNickname(str);
            model.addAttribute("blacklist", blacklist);
        }else if(mode.equals("phone")){
            // 연락처로 검색
            List<BlackDTO> blacklist = blackService.showBlacklistByPhone(str);
            model.addAttribute("blacklist", blacklist);
        }else if(mode.equals("inactive")){
            // 현재는 블랙리스트가 아닌 회원 검색
            List<BlackDTO> blacklist = blackService.showBlacklistByInactive();
            model.addAttribute("blacklist", blacklist);
        }

        return "black/blacklist";
    }

    /**
     * 해당 회원을 블랙리스트에 추가할지 확인하고 사유를 받도록 동작하는 메소드
     * */
    @PostMapping("/addBlacklist")
    public String addBlackReason(@RequestParam(name = "userNo") String userNo,
                                 @RequestParam(name = "auth") String auth,
                                 @RequestParam(name = "email") String email,
                                 @RequestParam(name = "nickname") String nickname,
                                 @RequestParam(name = "phone") String phone, Model model){
        // 해당회원의 정보 중 블랙리스트 정보에 들어갈 정보 뽑기
        model.addAttribute("userNo", userNo);
        model.addAttribute("auth", auth);
        model.addAttribute("email", email);
        model.addAttribute("nickname", nickname);
        model.addAttribute("phone", phone);

        return "black/addBlackReason";
    }

    /**
     * 회원관리 페이지에서 해당 회원을 블랙리스트로 변경하고 블랙리스트 명단에 입력하는 메소드
     * */
    @PostMapping("/insertBlacklist")
    public String addBlacklist(@RequestParam(name = "userNo") int userNo,
                               @RequestParam(name = "auth") String auth,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "reason") String reason, Model model){

        int change;

        if(Objects.isNull(blackService.searchBlackByEmailInAny(email))){
            // 블랙리스트에 등재된 적이 없다면
            // 블랙리스트에 등재하기
            System.out.println("insert");
            change = blackService.addBlacklist(userNo, auth, email, reason);
        }else {
            // 블랙리스트에 등재된 적이 있다면
            // 기존 정보에서 수정하기
            System.out.println("update");
            change = blackService.modifyBlacklist(userNo, auth, email, reason);
        }

        boolean chance = false;

        if(change > 0){
            chance = true;
        }

        int update = 0;

        if(chance){
            // 블랙리스트로 회원 권한을 변경
            System.out.println("메일이 " + email + "인 회원을 블랙리스트 상태로 변경합니다.");
            update = blackService.toBlacklist(email);
        }

        if(change > 0 && update > 0) {// 정상적인 블랙 처리
            model.addAttribute("blacklist", email + "회원이 블랙처리되었습니다.");
        }else {// 비정상적인 블랙 처리
            System.out.println("정상적으로 동작되지 않았습니다.");
            model.addAttribute("blacklist", "블랙처리에 실패하였습니다.");
        }

        return "admin/member";
    }

    /**
     * 블랙리스트 처리된 회원의 권한을 복구해줄 메소드
     * */
    @PostMapping("/restoreAuth")
    public String restoreAuth(@RequestParam(name = "userNo") int userNo,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "auth") String auth,
                              @RequestParam(name = "status")String status,
                              Model model){

        // 블랙리스트가 아닌 경우
        if(!status.equals("active")){
            model.addAttribute("restore", "현재 블랙리스트에 등재되어 있지 않습니다.");
            return "black/blacklist";
        }

        // 블랙리스트 명단에서 상태를 비활성화하고 수정 날짜 기록
        int change = blackService.disableBlack(userNo);

        // 블랙리스트 권한을 원 권한으로 변경
        int restore = blackService.restoreAuth(email, auth);

        if(change > 0 && restore > 0){
            model.addAttribute("restore", "성공적으로 권한이 복구되었습니다.");
        }else {
            model.addAttribute("restore", "권한 복구에 실패하였습니다.");
        }

        return "black/blacklist";
    }
}
