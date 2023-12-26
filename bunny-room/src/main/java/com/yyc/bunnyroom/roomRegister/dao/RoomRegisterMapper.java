package com.yyc.bunnyroom.roomRegister.dao;

import com.yyc.bunnyroom.roomRegister.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomRegisterMapper {
    int businessRegister(BusinessDTO businessDTO);

    List<BusinessCategoryDTO> selectAllBusinessCategory();

    int closedDaysRegister(List<ClosedDayDTO> list);

    int holidaysRegister(List<HolidayDTO> holidayList);

    List<RoomOptionDTO> selectAllRoomOptions();
}
