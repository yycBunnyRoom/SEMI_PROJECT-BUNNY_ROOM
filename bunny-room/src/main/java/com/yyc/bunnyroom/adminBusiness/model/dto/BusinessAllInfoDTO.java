package com.yyc.bunnyroom.adminBusiness.model.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class BusinessAllInfoDTO {

    private int businessNo;

    private String categoryName;

    private int registerNo;

    private String businessName;

    private String zipCode;

    private String address;

    private String businessPhone;

    private int startTime;

    private int endTime;

    private String owner;

    private String email;

    private String ownerPhone;

    private ZonedDateTime businessRegisterDate;

    private ZonedDateTime businessUpdateDate;

}
