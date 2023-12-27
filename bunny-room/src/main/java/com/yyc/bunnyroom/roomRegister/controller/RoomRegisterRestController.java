package com.yyc.bunnyroom.roomRegister.controller;

import com.yyc.bunnyroom.roomRegister.model.BusinessDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roomRegister/REST/")
public class RoomRegisterRestController {

    @Autowired
    RoomRegisterService roomRegisterService;







}
