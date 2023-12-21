package com.yyc.bunnyroom.adminSetting.controller;

import com.yyc.bunnyroom.adminSetting.model.dto.CategoryDTO;
import com.yyc.bunnyroom.adminSetting.service.AdminSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/setting")
@RequiredArgsConstructor
public class AdminSettingController {

    private final AdminSettingService adminSettingService;
    /**
     * 관리자 페이지 중 관리자 설정 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping(value = {"", "/"})
    public String setting(Model model){
        // 관리자 설정 페이지로 이동
        List<CategoryDTO> category = adminSettingService.showCategory();
        model.addAttribute("categorylist", category);
        return "admin/setting";
    }

    /**
     * 모든 카테고리를 출력해주는 메소드
     * */
    @GetMapping("/showCategory")
    public String showCategory(Model model){
        List<CategoryDTO> category = adminSettingService.showCategory();
        model.addAttribute("categorylist", category);

        return "admin/setting";
    }
}
