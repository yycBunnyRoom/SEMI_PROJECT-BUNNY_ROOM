package com.yyc.bunnyroom.inquiry.controller;

import com.yyc.bunnyroom.inquiry.dto.InquiryDTO;
import com.yyc.bunnyroom.inquiry.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("inquirys")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping
    public ModelAndView showAllInquiry(ModelAndView mv){

        List<InquiryDTO> inquiryList = inquiryService.showAllInquiry();

        if(Objects.isNull(inquiryList)){
            System.out.println("안보여");
        }
        mv.addObject("inquirys", inquiryList);
        mv.setViewName("inquiry/allInquirys");
        return mv;
    }
}
