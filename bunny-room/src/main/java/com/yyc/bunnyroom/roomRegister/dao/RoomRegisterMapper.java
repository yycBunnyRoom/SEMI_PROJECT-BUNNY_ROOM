package com.yyc.bunnyroom.roomRegister.dao;

import com.yyc.bunnyroom.roomRegister.model.BusinessCategoryDTO;
import com.yyc.bunnyroom.roomRegister.model.BusinessDTO;
import com.yyc.bunnyroom.roomRegister.model.ClosedDayDTO;
import com.yyc.bunnyroom.roomRegister.model.HolidayDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomRegisterMapper {
    int businessRegister(BusinessDTO businessDTO);

    List<BusinessCategoryDTO> selectAllBusinessCategory();

    int closedDaysRegister(List<ClosedDayDTO> list);

    int holidaysRegister(List<HolidayDTO> holidayList);
}
