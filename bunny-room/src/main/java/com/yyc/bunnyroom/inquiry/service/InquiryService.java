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
        List<InquiryDTO> list = inquiryDAO.showAllInquiry();

        if(Objects.isNull(list)){
            System.out.println("오류");
        }
        return list;
    }

    public InquiryDTO searchInquiry(int code){
        InquiryDTO contentslist = inquiryDAO.searchInquiry(code);

        if(Objects.isNull(contentslist)){
            throw new NullPointerException();
        }else{
            return contentslist;
        }
    }

    public int insertInquiry(InquiryDTO inquiryDTO){
        int result = inquiryDAO.insertInquiry(inquiryDTO);

        if(result <= 0){
            System.out.println("실패하였습니다.");
        }else{
            System.out.println("등록을 마쳤습니다.");
        }
        return result;
    }


    public int updateInquiry(InquiryDTO inquiryDTO){
        int result = inquiryDAO.updateInquiry(inquiryDTO);

        if(result <= 0){
            System.out.println("수정에 실패하셨습니다.");
        }else {
            System.out.println("수정이 완료되었습니다.");
        }
        return result;
    }
}
