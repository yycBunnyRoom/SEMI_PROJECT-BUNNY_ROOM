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
    private WeekDay closedDay;



    public ClosedDayDTO() {
    }

    public ClosedDayDTO(int closedDayIdx, int businessNo, WeekDay closedDay) {
        this.closedDayIdx = closedDayIdx;
        this.businessNo = businessNo;
        this.closedDay = closedDay;
    }
}
