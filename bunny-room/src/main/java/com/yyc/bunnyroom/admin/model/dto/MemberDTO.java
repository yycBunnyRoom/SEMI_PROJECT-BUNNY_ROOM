package com.yyc.bunnyroom.admin.model.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class MemberDTO {

    private int userNo;

    private String email;

    private String nickname;

    private String phone;

    private String status;

    private String auth;

    private ZonedDateTime userRegistDate;

    private ZonedDateTime userUpdateDate;

    private String withdrawReason;

}
