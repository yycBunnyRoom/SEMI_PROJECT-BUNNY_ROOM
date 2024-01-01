package com.yyc.bunnyroom.reservation.dao;

import com.yyc.bunnyroom.reservation.model.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {


    /* REGISTER */
    Integer addReservation(ReservationDTO newReservation);


    /* SELECT ALL */
    List<ReservationDTO> getReservationsByRoomNo(int roomNo);
}
