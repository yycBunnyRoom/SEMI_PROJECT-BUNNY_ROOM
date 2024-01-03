package com.yyc.bunnyroom.mypage.user.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class ReservationListDTO {

    private int reservationNo;

    private int userNo;

    private String businessName;

    private String roomName;

    private String businessPhone;

    private Date reservationDate;

    private String reservationTime;

    private int reservationPerson;

    private int totalCost;

    private String reservationStatus;

    private String cancelReason;

    private ZonedDateTime registerDate;

    private ZonedDateTime cancelDate;
}
