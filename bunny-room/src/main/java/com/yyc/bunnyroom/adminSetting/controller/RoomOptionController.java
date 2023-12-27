package com.yyc.bunnyroom.adminSetting.controller;

import com.yyc.bunnyroom.adminSetting.service.AdminSettingService;
import com.yyc.bunnyroom.roomRegister.model.RoomOptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/setting")
@RequiredArgsConstructor
public class RoomOptionController {

    private final AdminSettingService adminSettingService;

    /**
     * 모든 방 옵션을 조회해주는 메소드
     * */
    @GetMapping("/showOption")
    public List<RoomOptionDTO> showOption(){
        List<RoomOptionDTO> optionList = adminSettingService.showOption();

        return optionList;
    }

    /**
     * 방 옵션의 이름을 바꾸는 메소드
     * */
    @PostMapping("/changeOptionName")
    @ResponseBody
    public ResponseEntity<String> changeOptionName(@RequestBody RoomOptionDTO roomOptionDTO){
        int optionNo = roomOptionDTO.getOptionIdx();
        String newName = roomOptionDTO.getOptionName();

        int result = adminSettingService.changeOptionName(optionNo, newName);

        if(result > 0){
            return ResponseEntity.ok("Update successful");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update failed. ");
        }
    }
}
