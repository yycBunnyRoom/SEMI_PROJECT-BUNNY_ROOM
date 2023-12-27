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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/setting")
@RequiredArgsConstructor
public class AdminSettingController{

    private final AdminSettingService adminSettingService;

    private final RoomOptionController roomOptionController;

    /**
     * 관리자 페이지 중 관리자 설정 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping(value = {"", "/"})
    public String setting(Model model){
        // 관리자 설정 페이지로 이동
        showCategory(model);
        roomOptionController.showOption(model);
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
                         @RequestParam(name = "colorCode")String colorCode, RedirectAttributes redirectAttributes){

        int result = adminSettingService.changeCategory(categoryNo, categoryName, colorCode);

        if(result <= 0){
            System.out.println("스탑");
        }

        redirectAttributes.addFlashAttribute("message", "카테고리의 색이 변경되었습니다.");
        return "redirect:/admin/setting";
    }

    @PostMapping("/off")
    public String offCategory(@RequestParam(name = "categoryNo")int categoryNo){

        int result = adminSettingService.offCategory(categoryNo);

        if(result <= 0){
            System.out.println("스탑");
        }

        return "redirect:/admin/setting";
    }

    @GetMapping("toNewCategory")
    public String toNewCategory(){
        return "/admin/setting/categoryMaker";
    }

    @PostMapping("/newCategory")
    public String newCategory(@RequestParam(name = "categoryName")String categoryName,
                              @RequestParam(name = "colorCode")String colorCode, Model model){

        if(categoryName.isEmpty() || colorCode.isEmpty()){
            model.addAttribute("message", "값이 입력되지 않았습니다.");
            return "/admin/setting/categoryMaker";
        }

        int result = adminSettingService.newCategory(categoryName, colorCode);

        if(result > 0){
            model.addAttribute("message", "카테고리가 추가되었습니다.");
            return "redirect:/admin/setting";
        }else {
            model.addAttribute("message", "카테고리 추가에 실패했습니다.");
        }
        return "/admin/setting";
    }
}
