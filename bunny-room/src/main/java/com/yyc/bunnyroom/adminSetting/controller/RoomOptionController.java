package com.yyc.bunnyroom.adminSetting.controller;

import com.yyc.bunnyroom.adminSetting.service.AdminSettingService;
import com.yyc.bunnyroom.roomRegister.model.RoomOptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/setting")
@RequiredArgsConstructor
public class RoomOptionController {

    private final AdminSettingService adminSettingService;

    /**
     * 모든 방 옵션을 조회해주는 메소드
     * */
    @GetMapping("/showOption")
    public String showOption(Model model){
        List<RoomOptionDTO> optionList = adminSettingService.showOption();
        model.addAttribute("optionList", optionList);

        return "/admin/setting";
    }
}
