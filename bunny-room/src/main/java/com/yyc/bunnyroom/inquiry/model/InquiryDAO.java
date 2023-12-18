package com.yyc.bunnyroom.inquiry.model;

import com.yyc.bunnyroom.inquiry.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryDAO {

    List<InquiryDTO> showAllInquiry();
}
