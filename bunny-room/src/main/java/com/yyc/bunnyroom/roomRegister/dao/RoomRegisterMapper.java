package com.yyc.bunnyroom.roomRegister.dao;

import com.yyc.bunnyroom.roomRegister.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomRegisterMapper {

    //Register
    Integer businessRegister(BusinessDTO businessDTO);

    Integer roomRegister(RoomDTO newRoom);

    int closedDaysRegister(List<ClosedDayDTO> list);

    int holidaysRegister(List<HolidayDTO> holidayList);

    Integer appliedOptionsRegister(List<AppliedOptionDTO> appliedOptions);


    //SelectAll
    List<RoomOptionDTO> selectAllRoomOptions();

    List<BusinessDTO> getAllBusiness(int ownerNo);

    List<RoomDTO> getAllRooms(int businessIdx);

    List<BusinessCategoryDTO> selectAllBusinessCategory();


    //Select
    BusinessDTO getBusinessDetails(int businessNo);



}
