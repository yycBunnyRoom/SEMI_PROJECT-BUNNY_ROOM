package com.yyc.bunnyroom.inquiry.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class InquiryDTO {

    private int inquiryNo;
    private int userNo;
    private String inquiryTitle;
    private LocalDateTime inquiryRegistDate;
    private String inquiryContents;
    private LocalDateTime inquiryUpdateDate;
    private String inquiryStatus;
    private String userNickname;

    public InquiryDTO() {
    }

    public InquiryDTO(int inquiryNo, int userNo, String inquiryTitle, LocalDateTime inquiryRegistDate, String inquiryContents, LocalDateTime inquiryUpdateDate, String inquiryStatus, String userNickname) {
        this.inquiryNo = inquiryNo;
        this.userNo = userNo;
        this.inquiryTitle = inquiryTitle;
        this.inquiryRegistDate = inquiryRegistDate;
        this.inquiryContents = inquiryContents;
        this.inquiryUpdateDate = inquiryUpdateDate;
        this.inquiryStatus = inquiryStatus;
        this.userNickname = userNickname;
    }

    public int getInquiryNo() {
        return inquiryNo;
    }

    public void setInquiryNo(int inquiryNo) {
        this.inquiryNo = inquiryNo;
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

    public LocalDateTime getInquiryRegistDate() {
        return inquiryRegistDate;
    }

    public void setInquiryRegistDate(LocalDateTime inquiryRegistDate) {
        this.inquiryRegistDate = inquiryRegistDate;
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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Override
    public String toString() {
        return "InquiryDTO{" +
                "inquiryNo=" + inquiryNo +
                ", userNo=" + userNo +
                ", inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryRegistDate=" + inquiryRegistDate +
                ", inquiryContents='" + inquiryContents + '\'' +
                ", inquiryUpdateDate=" + inquiryUpdateDate +
                ", inquiryStatus='" + inquiryStatus + '\'' +
                ", userNickname='" + userNickname + '\'' +
                '}';
    }
}
