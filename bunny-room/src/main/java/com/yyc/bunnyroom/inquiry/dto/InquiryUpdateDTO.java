package com.yyc.bunnyroom.inquiry.dto;

import java.time.LocalDateTime;

public class InquiryUpdateDTO {


    private int userNo;
    private String inquiryTitle;


    private String inquiryContents;

    private LocalDateTime inquiryUpdateDate;

    private String inquiryStatus;


    public InquiryUpdateDTO() {
    }

    public InquiryUpdateDTO(int userNo, String inquiryTitle, String inquiryContents, LocalDateTime inquiryUpdateDate, String inquiryStatus) {
        this.userNo = userNo;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContents = inquiryContents;
        this.inquiryUpdateDate = inquiryUpdateDate;
        this.inquiryStatus = inquiryStatus;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
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

    public LocalDateTime getInquiryUpdateDate() {
        return inquiryUpdateDate;
    }

    public void setInquiryUpdateDate(LocalDateTime inquiryUpdateDate) {
        this.inquiryUpdateDate = inquiryUpdateDate;
    }

    public String getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(String inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    @Override
    public String toString() {
        return "InquiryUpdateDTO{" +
                "userNo=" + userNo +
                ", inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryContents='" + inquiryContents + '\'' +
                ", inquiryUpdateDate=" + inquiryUpdateDate +
                ", inquiryStatus='" + inquiryStatus + '\'' +
                '}';
    }
}
