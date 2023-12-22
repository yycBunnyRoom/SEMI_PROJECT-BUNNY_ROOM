package com.yyc.bunnyroom.inquiry.controller;

import ch.qos.logback.classic.Logger;
import com.yyc.bunnyroom.inquiry.dto.InquiryDTO;
import com.yyc.bunnyroom.inquiry.dto.InquiryRegistDTO;
import com.yyc.bunnyroom.inquiry.dto.InquiryUpdateDTO;
import com.yyc.bunnyroom.inquiry.service.InquiryService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import com.yyc.bunnyroom.signup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("inquirys")
public class InquiryController {

    @Autowired
    private UserService userService;

    @Autowired
    private InquiryService inquiryService;

    @GetMapping
    public ModelAndView showAllInquiry(ModelAndView mv){

        List<InquiryDTO> list = inquiryService.showAllInquiry();

        if(Objects.isNull(list)){
            System.out.println("안보여");
        }
        mv.addObject("inquirys", list);
        mv.setViewName("inquiry/allInquirys");
        return mv;
    }

//    @GetMapping
//    public String showInquiryContent(@RequestParam("code") int code, Model model) {
//        // 내용을 검색하고 뷰에 전달하는 로직
//        return "inquiry/allinquirysContent"; // "inquiryContent"가 Thymeleaf 템플릿 이름이라고 가정
//    }


    @GetMapping("/searchInquiry")
    public ModelAndView searchInquiry(ModelAndView mv, @RequestParam("inquiryNo") int inquiryNo){

        InquiryDTO contentslist = inquiryService.searchInquiry(inquiryNo);

        if(Objects.isNull(contentslist)){
            throw new NullPointerException();
        }else{
            mv.addObject("contentslist", contentslist);
            mv.setViewName("inquiry/allinquirysContent");
            return mv;

        }
    }



    @GetMapping("/insert_inquiry")
    public ModelAndView insertInquiry(ModelAndView mv) {
        mv.addObject("inquiryDTO", new InquiryDTO());
        mv.setViewName("inquiry/inquiryInsert"); // 뷰 이름 설정
        return mv;
    }

    @PostMapping("/insert")
    public String insertInquiryPage(@ModelAttribute InquiryDTO inquiryDTO){
//        String loggedUser = principal.getName();
//        int userNo = Integer.parseInt(loggedUser);

        //회원테이블과 대조해서 거기서 userNo 가져오는 방향으로

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();


            InquiryDTO inquiry = new InquiryDTO();
            inquiry.setUserNo(userDetails.getLoginUserDTO().getUserNo());
            inquiry.setInquiryTitle(inquiryDTO.getInquiryTitle());
            inquiry.setInquiryContents(inquiryDTO.getInquiryContents());

            inquiryService.insertInquiry(inquiry);

        }
        return "redirect:/inquirys";
    }





    @GetMapping("/update_inquiry")
    public String updateInquiryView(){
        return "inquiry/inquiryUpdate";
    }

    @PostMapping("/updateInquiry")
    public String updateInquiry(InquiryDTO inquiryDTO){
        inquiryService.updateInquiry(inquiryDTO);

        return "redirect:/inquirys";
    }



}
