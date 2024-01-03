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
        return "/admin/business";
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

        return "/admin/business";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "businessNo") int businessNo,
                         @RequestParam(name = "status") String status, Model model, RedirectAttributes redirectAttributes){
        // 만약 이미 삭제된 업체라면?
        if(status.equals("inactive")){
            redirectAttributes.addFlashAttribute("message", "이미 삭제 처리된 업체입니다.");
            return "redirect:/admin/business/search?mode=inactive&target=";
        }

        model.addAttribute("businessNo", businessNo);
        model.addAttribute("status", status);
        return "/admin/business/deleteReason";
    }

    @PostMapping("/deleteReason")
    public String deleteReason(@RequestParam(name = "businessNo")int businessNo,
                               @RequestParam(name = "reason")String reason, RedirectAttributes redirectAttributes){

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

    @PostMapping("/restore")
    public String restore(@RequestParam(name = "businessNo") int businessNo,
                          @RequestParam(name = "status") String status,
                          @RequestParam(name = "reason") String reason, Model model, RedirectAttributes redirectAttributes){
        // 만약 이미 복구되거나 삭제되지 않은 업체라면
        if(status.equals("active")){
            redirectAttributes.addFlashAttribute("message", "삭제된 업체가 아닙니다.");
            return "redirect:/admin/business";
        }

        model.addAttribute("businessNo", businessNo);
        model.addAttribute("status", status);
        model.addAttribute("reason", reason);

        return "/admin/business/restoreReason";
    }

    @PostMapping("/restoreReason")
    public String restoreReason(@RequestParam(name = "businessNo") int businessNo,
                                @RequestParam(name = "deleteReason") String deleteReason,
                                @RequestParam(name = "restoreReason") String restoreReason, RedirectAttributes redirectAttributes){
        String reason = "삭제사유는 '" + deleteReason + "' 였으며, 복구 사유는 '" + restoreReason + "' 입니다.";
        int result = adminBusinessService.restore(businessNo, reason);

        if(result > 0){
            System.out.println("정상적으로 복구되었습니다.");
            redirectAttributes.addFlashAttribute("message", "업체가 정삭적으로 복구되었습니다.");
        }else {
            System.out.println("복구 실패");
            redirectAttributes.addFlashAttribute("message", "복구에 실패하셨습니다.");
        }

        return "redirect:/admin/business";
    }
}
