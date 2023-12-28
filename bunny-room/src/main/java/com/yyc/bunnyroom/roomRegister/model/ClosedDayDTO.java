package com.yyc.bunnyroom.roomRegister.model;

import com.yyc.bunnyroom.common.eNum.WeekDay;
import lombok.Data;

@Data
public class ClosedDayDTO {

//    CLOSED_DAY_NO		INTEGER
//    BUSINESS_NO		INTEGER
//    CLOSED_DAY		VARCHAR

    private int closedDayIdx;
    private int businessNo;
    private String closedDay;

}
