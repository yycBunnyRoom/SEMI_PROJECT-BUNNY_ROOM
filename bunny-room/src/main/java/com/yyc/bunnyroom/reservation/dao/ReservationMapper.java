package com.yyc.bunnyroom.reservation.dao;

import com.yyc.bunnyroom.reservation.model.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {


    Integer addReservation(ReservationDTO newReservation);
}
