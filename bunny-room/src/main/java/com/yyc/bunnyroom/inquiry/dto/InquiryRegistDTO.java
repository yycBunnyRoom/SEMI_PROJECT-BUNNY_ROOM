package com.yyc.bunnyroom.inquiry.dto;

import java.time.LocalDateTime;

public class InquiryRegistDTO {

    private String inquiryTitle;

    private String inquiryContents;



    public InquiryRegistDTO() {
    }

    public InquiryRegistDTO(String inquiryTitle, String inquiryContents) {
        this.inquiryTitle = inquiryTitle;
        this.inquiryContents = inquiryContents;
    }

    public String getInquiryTitle() {
        return inquiryTitle;
    }

    public void setInquiryTitle(String inquiryTitle) {
        this.inquiryTitle = inquiryTitle;
    }

    public String getInquiryContents() {
        return inquiryContents;
    }

    public void setInquiryContents(String inquiryContents) {
        this.inquiryContents = inquiryContents;
    }

    @Override
    public String toString() {
        return "InquiryRegistDTO{" +
                "inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryContents='" + inquiryContents + '\'' +
                '}';
    }
}
