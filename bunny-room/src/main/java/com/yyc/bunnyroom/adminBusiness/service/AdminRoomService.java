package com.yyc.bunnyroom.adminBusiness.service;

import com.yyc.bunnyroom.adminBusiness.model.dao.AdminRoomDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminRoomService {

    private final AdminRoomDAO adminRoomDAO;
}
