package com.yyc.bunnyroom.roomRegister.model;

import lombok.Data;

@Data
public class TimeUnitScheduleDTO {

//    이용시간 번호	TIME_UNIT_SCHEDULE_NO		INTEGER
//    사업체 번호	BUSINESS_NO		INTEGER
//    시작시간	START_TIME		INTEGER
//    종료시간	END_TIME		INTEGER
//    주말여부	WEEKEND_TIME_UNIT_STATUS		VARCHAR

    private int timeUnitScheduleIdx;
    private int businessNo;
    private int startTime;
    private int endTime;
    private String weekendTimeUnitStatus;
}
