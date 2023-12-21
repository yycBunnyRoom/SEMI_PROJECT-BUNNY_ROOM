package com.yyc.bunnyroom.adminSetting.controller;

import com.yyc.bunnyroom.adminSetting.model.dto.CategoryDTO;
import com.yyc.bunnyroom.adminSetting.service.AdminSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/setting")
@RequiredArgsConstructor
public class AdminSettingController{

    private final AdminSettingService adminSettingService;
    /**
     * 관리자 페이지 중 관리자 설정 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping(value = {"", "/"})
    public String setting(Model model){
        // 관리자 설정 페이지로 이동
        showCategory(model);
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

    /**
     * 변경하고자 하는 카테고리 정보를 다음 과정으로 전달해주는 메소드
     * */
    @PostMapping("/callChanger")
    public ModelAndView changeCategory(@RequestParam(name = "categoryNo")int categoryNo,
                                       @RequestParam("categoryName")String categoryName,
                                       @RequestParam("colorCode")String colorCode, ModelAndView mv){
        mv.addObject("categoryNo", categoryNo);
        mv.addObject("categoryName", categoryName);
        mv.addObject("colorCode", colorCode);
        mv.setViewName("admin/setting/categoryChanger");
        return mv;
    }

    /**
     * 전달받은 카테고리 정보를 통해 값을 변경하는 메소드
     * */
    @PostMapping("/change")
    public String change(@RequestParam(name = "categoryNo")int categoryNo,
                         @RequestParam(name = "categoryName")String categoryName,
                         @RequestParam(name = "colorCode")String colorCode){

        int result = adminSettingService.changeCategory(categoryNo, categoryName, colorCode);

        if(result <= 0){
            System.out.println("스탑");
        }

        return "redirect:/admin/setting";
    }

    @PostMapping("/off")
    private String offCategory(@RequestParam(name = "categoryNo")int categoryNo){

        int result = adminSettingService.offCategory(categoryNo);

        if(result <= 0){
            System.out.println("스탑");
        }

        return "redirect:/admin/setting";
    }
}
