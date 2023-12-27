package com.yyc.bunnyroom.roomRegister.controller;

import com.yyc.bunnyroom.roomRegister.model.BusinessDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/roomRegister/business")
public class BusinessController {
    @Autowired
    RoomRegisterService roomRegisterService;


    /* 모든 사업체를 가져오는 메소드 */
    @GetMapping("/getAllBusiness")
    public List<BusinessDTO> getAllBusiness() {

        /* businessDTO 에 사용자 번호 입력*/
        // 현재 사용중인 사용자를 지정
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 로그인 되어있는 SecurityContextHolder 안에 있는 AuthDetails 정보를 가져옴
        // AuthDetails 안에 있는 userNo 정보를 가져옴
        int ownerNo;
        ownerNo = ((AuthDetails)currentUser).getLoginUserDTO().getUserNo();

        return roomRegisterService.getAllBusiness(ownerNo);
    }



    /* 업체 등록 */
    @PostMapping("/register")
    public ResponseEntity<Integer> businessRegister(@RequestBody BusinessDTO businessDTO){

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

        /* 사업체를 등록시킨다 */
        Integer result = roomRegisterService.businessRegister(businessDTO);

        if (result != null && result > 0) {
            // 성공적으로 등록됨을 나타내는 1 반환
            return ResponseEntity.ok(1);
        } else {
            // 등록 실패를 나타내는 0 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }

    }




    /* 사업체 상세 페이지로 이동 */
    @GetMapping("/businessDetail/{businessNo}")
    public ModelAndView getBusinessDetails(@PathVariable("businessNo") int businessNo) {
        ModelAndView modelAndView = new ModelAndView();

        System.out.println("이동할 상세페이지의 사업체번호: "+businessNo);
        BusinessDTO businessDetails =roomRegisterService.getBusinessDetails(businessNo);
        modelAndView.addObject("businessDetails",businessDetails);


        System.out.println("room 찾기전 확인"+businessNo);
        // businessNo에 상응하는 Room을 가져간다
        List<RoomDTO> roomList = getAllRooms(businessNo);
        System.out.println("roomList 유무 확인: "+roomList);
        modelAndView.addObject("roomList", roomList);

        /*if (roomList != null){
            modelAndView.addObject("roomList", roomList);
        }*/

        modelAndView.setViewName("/roomRegister/detail/businessDetail");
        return modelAndView;
    }

    /* Detail 페이지 가기 전에 상응하는 방을 찾아서 간다*/
    public List<RoomDTO> getAllRooms(int businessNo){
        return roomRegisterService.getAllRooms(businessNo);
    }





}
