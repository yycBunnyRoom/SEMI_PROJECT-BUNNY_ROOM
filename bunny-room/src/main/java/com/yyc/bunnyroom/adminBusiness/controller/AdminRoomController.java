package com.yyc.bunnyroom.adminBusiness.controller;

import com.yyc.bunnyroom.adminBusiness.service.AdminRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/business/room")
@RequiredArgsConstructor
public class AdminRoomController {

    private final AdminRoomService adminRoomService;


}
