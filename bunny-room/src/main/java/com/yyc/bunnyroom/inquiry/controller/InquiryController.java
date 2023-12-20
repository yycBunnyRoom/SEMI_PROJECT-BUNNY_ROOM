package com.yyc.bunnyroom.inquiry.controller;

import com.yyc.bunnyroom.inquiry.dto.InquiryDTO;
import com.yyc.bunnyroom.inquiry.dto.InquiryRegistDTO;
import com.yyc.bunnyroom.inquiry.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("inquirys")
public class InquiryController {

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
        mv.addObject("inquiryRegistDTO", new InquiryRegistDTO());
        mv.setViewName("inquiry/inquiryInsert"); // 뷰 이름 설정
        return mv;
    }

    @PostMapping("/insert")
    public String insertInquiryPage(@ModelAttribute("inquiryRegistDTO") InquiryRegistDTO inquiryRegistDTO, Principal principal){
//        String loggedUser = principal.getName();
//        int userNo = Integer.parseInt(loggedUser);

        Object principals = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        InquiryDTO inquiry = new InquiryDTO();
        inquiry.setUserNo(userNo);
        inquiry.setInquiryTitle(inquiryRegistDTO.getInquiryTitle());
        inquiry.setInquiryContents(inquiryRegistDTO.getInquiryContents());

        inquiryService.insertInquiry(inquiry);

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
