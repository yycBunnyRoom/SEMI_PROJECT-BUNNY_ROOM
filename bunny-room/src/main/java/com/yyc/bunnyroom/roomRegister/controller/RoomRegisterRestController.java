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


    /* 모든 사업체를 가져오는 메소드 */
    @GetMapping("/getAllBusiness")
    public List<BusinessDTO> getAllBusiness() {

        System.out.println("입장 하였는가?");



        /* businessDTO 에 사용자 번호 입력*/
        // 현재 사용중인 사용자를 지정
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 로그인 되어있는 SecurityContextHolder 안에 있는 AuthDetails 정보를 가져옴
        // AuthDetails 안에 있는 userNo 정보를 가져옴
        int ownerNo;
        ownerNo = ((AuthDetails)currentUser).getLoginUserDTO().getUserNo();

        List<BusinessDTO> businessList = roomRegisterService.getAllBusiness(ownerNo);
        System.out.println(businessList);

        return businessList;
    }


    /* 사업체 상세 페이지로 이동 */
    @GetMapping("/businessDetailPage/{businessNo}")
    public BusinessDTO getBusinessDetails(@PathVariable int businessNo) {
        return roomRegisterService.getBusinessDetails(businessNo);
    }

}
