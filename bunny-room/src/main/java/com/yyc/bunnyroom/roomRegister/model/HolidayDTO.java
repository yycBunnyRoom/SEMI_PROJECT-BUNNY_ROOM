package com.yyc.bunnyroom.roomRegister.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Data
public class HolidayDTO {
//    HOLIDAY_NO		INTEGER
//    BUSINESS_NO		INTEGER
//    HOLIDAY_NAME		VARCHAR
//    HOLIDAY_REASON		VARCHAR
//    HOLIDAY_START_DATE		TIMESTAMP
//    HOLIDAY_END_DATE		TIMESTAMP
//    HOLIDAY_REGIST_DATE		TIMESTAMP
//    HOLIDAY_UPDATE_DATE		TIMESTAMP
//    HOLIDAY_STATUS		VARCHAR

    private int holidayIdx;
    private int businessNo;
    private String holidayName;
    private String reason;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private ZonedDateTime registDate;
    private ZonedDateTime updateDate;
    private String activeStatus;

}
