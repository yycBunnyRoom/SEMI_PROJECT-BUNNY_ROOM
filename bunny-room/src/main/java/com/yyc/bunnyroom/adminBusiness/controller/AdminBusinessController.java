package com.yyc.bunnyroom.adminBusiness.controller;

import com.yyc.bunnyroom.adminBusiness.model.dto.AdminBusinessDTO;
import com.yyc.bunnyroom.adminBusiness.service.AdminBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/business")
@RequiredArgsConstructor
public class AdminBusinessController {

    private final AdminBusinessService adminBusinessService;

    /**
     * 관리자 페이지 중 업체 관리 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping(value = {"","/"})
    public String business(Model model){
        // 관리자 업체 관리 페이지로 이동
        List<AdminBusinessDTO> BusinessList = adminBusinessService.allSearcher();
        model.addAttribute("BusinessList", BusinessList);
        return "admin/business";
    }

    @GetMapping("/search")
    public String businessSearcher(@RequestParam("mode")String mode,
                                   @RequestParam("target")String target, Model model){

        List<AdminBusinessDTO> BusinessList;

        if(mode.equals("businessName")){
            // 업체명 검색
            BusinessList = adminBusinessService.nameSearcher(target);
        }else if(mode.equals("category")){
            // 카테고리 검색
            BusinessList = adminBusinessService.categorySearcher(target);
        }else if(mode.equals("RegistNo")) {
            // 등록증 번호 검색
            BusinessList = adminBusinessService.registNoSearcher(target);
        } else if(mode.equals("email")) {
            // 이메일 검색
            BusinessList = adminBusinessService.emailSearcher(target);
        }else if(mode.equals("nickname")) {
            // 닉네임 검색
            BusinessList = adminBusinessService.nicknameSearcher(target);
        }else if(mode.equals("address")){
            // 주소 검색
            BusinessList = adminBusinessService.addressSearcher(target);
        }else if(mode.equals("phone")) {
            // 연락처 검색
            BusinessList = adminBusinessService.phoneSearcher(target);
        }else if(mode.equals("inactive")){
            // 삭제된 내용 검색
            BusinessList = adminBusinessService.inactiveSearcher();
        }else {
            // 전체 검색
            BusinessList = adminBusinessService.allSearcher();
            }

        model.addAttribute("BusinessList", BusinessList);

        return "admin/business";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "businessNo") int businessNo, Model model){
        model.addAttribute("businessNo", businessNo);
        return "admin/business/deleteReason";
    }

    @PostMapping("/deleteReason")
    public String deleteReason(@RequestParam(name = "businessNo")int businessNo, @RequestParam(name = "reason")String reason, RedirectAttributes redirectAttributes){
        int result = adminBusinessService.delete(businessNo, reason);

        if(result > 0){
            System.out.println("정상적으로 삭제되었습니다.");
            redirectAttributes.addFlashAttribute("message", "업체가 정삭적으로 삭제되었습니다.");
        }else {
            System.out.println("삭제 실패");
            redirectAttributes.addFlashAttribute("message", "삭제에 실패하셨습니다.");
        }

        return "redirect:/admin/business";
    }
}
