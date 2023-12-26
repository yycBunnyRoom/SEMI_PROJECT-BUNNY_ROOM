package com.yyc.bunnyroom.roomRegister.model;

import lombok.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
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


    private Date startDate;
    private Date endDate;




//    // 입력받는 값은 Date로 받음
//    private Date inputStartDate;
//    private Date inputEndDate;
//
//    //실질적인 데이터
//    private ZonedDateTime startDate;
//    private ZonedDateTime endDate;



    private ZonedDateTime registDate;
    private ZonedDateTime updateDate;
    private String activeStatus;

//    // Builder를 통한 객체 생성 시 Date를 ZonedDateTime으로 변환
//    @Builder(toBuilder = true)
//    public HolidayDTO(
//            int holidayIdx,
//            int businessNo,
//            String holidayName,
//            String reason,
//            Date inputStartDate,
//            Date inputEndDate,
//            ZonedDateTime startDate,
//            ZonedDateTime endDate,
//            ZonedDateTime registDate,
//            ZonedDateTime updateDate,
//            String activeStatus
//    ) {
//        this.holidayIdx = holidayIdx;
//        this.businessNo = businessNo;
//        this.holidayName = holidayName;
//        this.reason = reason;
//        this.inputStartDate = inputStartDate;
//        this.inputEndDate = inputEndDate;
//        this.startDate = convertToZonedDateTime(inputStartDate);
//        this.endDate = convertToZonedDateTime(inputEndDate);
//        this.registDate = registDate;
//        this.updateDate = updateDate;
//        this.activeStatus = activeStatus;
//    }
//
//    // 자동으로 Date 형식을 ZoneDateTime으로 변환시켜주는 메소드
//    private ZonedDateTime convertToZonedDateTime(Date date) {
//        if (date != null) {
//            Instant instant = date.toInstant();
//            return instant.atZone(ZoneId.systemDefault());
//        }
//        return null;
//    }


}
