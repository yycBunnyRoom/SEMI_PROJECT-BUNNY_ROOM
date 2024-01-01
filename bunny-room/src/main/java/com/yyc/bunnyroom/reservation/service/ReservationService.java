package com.yyc.bunnyroom.reservation.service;

import com.yyc.bunnyroom.reservation.dao.ReservationMapper;
import com.yyc.bunnyroom.reservation.model.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {

    @Autowired
    ReservationMapper reservationMapper;


    public Integer addReservation(ReservationDTO newReservation) {
        return reservationMapper.addReservation(newReservation);
    }
}
