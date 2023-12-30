package com.yyc.bunnyroom.roomRegister.dao;

import com.yyc.bunnyroom.reservation.model.KeywordDTO;
import com.yyc.bunnyroom.roomRegister.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface RoomRegisterMapper {

    //Register
    Integer businessRegister(BusinessDTO businessDTO);

    Integer roomRegister(RoomDTO newRoom);

    int closedDaysRegister(List<ClosedDayDTO> list);

    int holidaysRegister(List<HolidayDTO> holidayList);

    Integer appliedOptionsRegister(List<AppliedOptionDTO> appliedOptions);

    Integer addTimeSchedule(List<TimeUnitScheduleDTO> timeUnitScheduleList);


    //SelectAll
    List<RoomOptionDTO> selectAllRoomOptions();

    List<BusinessDTO> getAllBusiness(int ownerNo);

    List<RoomDTO> getRoomsByBusinessNo(int businessIdx);

    List<BusinessCategoryDTO> selectAllBusinessCategory();

    List<AppliedOptionDTO> getAppliedOptions(int roomNo);

    List<ClosedDayDTO> getAllClosedDays(int businessNo);

    List<RoomDTO> getRoomsByCategory(String businessCategoryName);

    List<RoomDTO> getAllRooms();


    //Select
    BusinessDTO getBusinessDetails(int businessNo);

    RoomDTO getRoomDetails(int roomNo);


    List<RoomDTO> getRoomsByKeyword(KeywordDTO keywordDTO);


}
