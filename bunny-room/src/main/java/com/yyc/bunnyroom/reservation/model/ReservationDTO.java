package com.yyc.bunnyroom.reservation.model;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class ReservationDTO {

//    RESERVATION_NO	int
//    USER_NO	int
//    RESERVATION_DATE	timestamp
//    RESERVATION_UNIT	varchar(100)
//    RESERVATION_PERSON	int
//    RESERVATION_TOTAL_COST	int
//    RESERVATION_STATUS	varchar(20)
//    RESERVATION_CANCEL_REASON	varchar(500)
//    RESERVATION_CANCEL_DATE	timestamp
//    RESERVATION_REGIST_DATE	timestamp
//    ROOM_NO	int

    private int reservationIdx;
    private int userNo;
    private ZonedDateTime reservationDate;
    private String reservationUnit;
    private int people;
    private int totalCost;
    private String reservationStatus;

//    default ='예약중',
//            '완료됨',
//            '취소됨'

    private String reservationCancelReason;
    private ZonedDateTime reservationCancelDate;
    private ZonedDateTime reservationRegistDate;
    private int roomNo;



    private Date date;
    private String roomName;


}
