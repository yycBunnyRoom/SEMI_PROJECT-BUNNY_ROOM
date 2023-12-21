package com.yyc.bunnyroom.roomRegister.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BusinessDTO {
//    BUSINESS_NO		INTEGER
//    USER_NO		INTEGER
//    BUSINESS_REGIST_NO		INTEGER
//    BUSINESS_NAME		VARCHAR
//    BUSINESS_CATEGORY_NO		INTEGER
//    BUSINESS_ADDRESS_ROAD		VARCHAR
//    BUSINESS_ADDRESS_DETAIL		VARCHAR
//    BUSINESS_ZIP_CODE		VARCHAR
//    BUSINESS_PHONE		VARCHAR
//    BUSINESS_REGIST_DATE		TIMESTAMP
//    BUSINESS_UPDATE_DATE		TIMESTAMP
//    BUSINESS_STATUS		VARCHAR

    private int businessNo;
    private int userNo; //tbl_user의 userNo의 foreignKey
    private int businessRegistNo; //호스트의 사업자번호
    private String businessName;
    private int businessCategoryNo;
    private String businessAddressRoad;
    private String businessAddressDetail;
    private String businessZipCode;
    private String businessPhone;
    private ZonedDateTime businessRegistDate;
    private ZonedDateTime businessUpdateDate;
    private String businessStatus;





}



