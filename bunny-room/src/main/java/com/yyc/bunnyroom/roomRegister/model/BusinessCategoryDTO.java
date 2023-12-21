package com.yyc.bunnyroom.roomRegister.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BusinessCategoryDTO {
//    BUSINESS_CATEGORY_NO		INTEGER
//    BUSINESS_CATEGORY_NAME		VARCHAR
//    BUSINESS_CATEGORY_COLOR_CODE		VARCHAR
//    BUSINESS_CATEGORY_STATUS		VARCHAR

    private int businessCategoryNo;
    private String businessCategoryName;
    private String businessCategoryColorCode;
    private String businessCategoryStatus;

}
