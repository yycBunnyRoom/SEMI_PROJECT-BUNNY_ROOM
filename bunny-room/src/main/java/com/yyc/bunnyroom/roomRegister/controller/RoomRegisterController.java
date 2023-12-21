package com.yyc.bunnyroom.roomRegister.controller;

import com.yyc.bunnyroom.common.UserRole;
import com.yyc.bunnyroom.roomRegister.dao.RoomRegisterMapper;
import com.yyc.bunnyroom.roomRegister.model.BusinessCategoryDTO;
import com.yyc.bunnyroom.roomRegister.model.BusinessDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;
import java.util.List;


@Controller
@RequestMapping("/roomRegister")
public class RoomRegisterController {

    @Autowired
    RoomRegisterService roomRegisterService;


    // 업체등록 페이지로 이동하는 컨드롤러
    @GetMapping("/hostMainView")
    public String hostMainView(){
        return "/roomRegister/view/hostMainView";
    }

    @GetMapping("/businessRegisterForm")
    public ModelAndView businessRegisterForm(ModelAndView modelAndView){

        // DB 에 있는 카테고리를 가져와서 같이 보낸다
        List<BusinessCategoryDTO> businessCategoryList = roomRegisterService.selectAllBusinessCategory();

        modelAndView.addObject("businessCategoryList",businessCategoryList);

        modelAndView.setViewName("/roomRegister/form/businessRegisterForm");

        return modelAndView;
    }

    @PostMapping("/businessRegister")
    public ModelAndView businessRegister(@ModelAttribute BusinessDTO businessDTO, ModelAndView modelAndView){

        /* businessDTO 에 사용자 번호 입력*/
        // 현재 사용중인 사용자를 지정
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 로그인 되어있는 SecurityContextHolder 안에 있는 AuthDetails 정보를 가져옴
        // AuthDetails 안에 있는 userNo 정보를 가져옴
        int ownerNo;
        ownerNo = ((AuthDetails)currentUser).getLoginUserDTO().getUserNo();

        // businessDTO 에 가져온 userNo 를 저장
        businessDTO.setUserNo(ownerNo);

        /* businessDTO 에 businessRegistDate 입력*/
        ZonedDateTime currentTime = ZonedDateTime.now();
        businessDTO.setBusinessRegistDate(currentTime);

        /* businessDTO 상태 active*/
        businessDTO.setBusinessStatus("active");





        /* test */
        System.out.println(businessDTO.getBusinessCategoryNo());




        /* 사업체를 등록시킨다 */
        int result = roomRegisterService.businessRegister(businessDTO);

        /* 등록 성공, 실패 controller */
        String message ="";
        if (result == 1){
            message = "사업체를 성공적으로 등록하셨습니다.";
            modelAndView.addObject("message", message);
        }else {
            message = "사업체를 등록 실패했습니다";
            modelAndView.addObject("message", message);
        }

        modelAndView.setViewName("/roomRegister/view/hostMainView");
        return modelAndView;

    }


}
