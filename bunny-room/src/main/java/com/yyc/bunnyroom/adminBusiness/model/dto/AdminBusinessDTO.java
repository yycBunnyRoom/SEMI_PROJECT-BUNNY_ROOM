package com.yyc.bunnyroom.adminBusiness.model.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AdminBusinessDTO {

    private int businessNo;

    private String categoryName;

    private String businessName;

    private int businessRegistNo;

    private String email;

    private String nickname;

    private String zipCode;

    private String address;

    private String phone;

    private ZonedDateTime registDate;

    private ZonedDateTime updateDate;

    private String reason;

    private String status;
}
