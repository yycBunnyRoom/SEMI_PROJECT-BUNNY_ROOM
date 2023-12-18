package com.yyc.bunnyroom.inquiry.service;

import com.yyc.bunnyroom.inquiry.dto.InquiryDTO;
import com.yyc.bunnyroom.inquiry.model.InquiryDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InquiryService {

    private InquiryDAO inquiryDAO;

    public InquiryService(InquiryDAO inquiryDAO) {
        this.inquiryDAO = inquiryDAO;
    }

    public List<InquiryDTO> showAllInquiry(){
        List<InquiryDTO> inquiryList = inquiryDAO.showAllInquiry();

        if(Objects.isNull(inquiryList)){
            System.out.println("오류");
        }
        return inquiryList;
    }
}
