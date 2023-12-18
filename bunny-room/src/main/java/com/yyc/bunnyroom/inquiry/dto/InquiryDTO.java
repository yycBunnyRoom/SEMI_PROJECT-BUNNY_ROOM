package com.yyc.bunnyroom.inquiry.dto;

import java.sql.Date;

public class InquiryDTO {

    private int code;
    private int userNo;
    private String inquiryTitle;
    private Date inquiryRegistDate;
    private String inquiryContents;
    private Date inquiryUpdateDate;
    private String inquiryStatus;

    public InquiryDTO(){

    }

    public InquiryDTO(int code, int userNo, String inquiryTitle, Date inquiryRegistDate, String inquiryContents, Date inquiryUpdateDate, String inquiryStatus) {
        this.code = code;
        this.userNo = userNo;
        this.inquiryTitle = inquiryTitle;
        this.inquiryRegistDate = inquiryRegistDate;
        this.inquiryContents = inquiryContents;
        this.inquiryUpdateDate = inquiryUpdateDate;
        this.inquiryStatus = inquiryStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public Date getInquiryRegistDate() {
        return inquiryRegistDate;
    }

    public void setInquiryRegistDate(Date inquiryRegistDate) {
        this.inquiryRegistDate = inquiryRegistDate;
    }

    public String getInquiryContents() {
        return inquiryContents;
    }

    public void setInquiryContents(String inquiryContents) {
        this.inquiryContents = inquiryContents;
    }

    public Date getInquiryUpdateDate() {
        return inquiryUpdateDate;
    }

    public void setInquiryUpdateDate(Date inquiryUpdateDate) {
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
        return "InquiryDTO{" +
                "code=" + code +
                ", userNo=" + userNo +
                ", inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryRegistDate=" + inquiryRegistDate +
                ", inquiryContents='" + inquiryContents + '\'' +
                ", inquiryUpdateDate=" + inquiryUpdateDate +
                ", inquiryStatus='" + inquiryStatus + '\'' +
                '}';
    }
}
