package com.yyc.bunnyroom.adminBusiness.model.dao;

import com.yyc.bunnyroom.adminBusiness.model.dto.BusinessAllInfoDTO;
import com.yyc.bunnyroom.roomRegister.model.ClosedDayDTO;
import com.yyc.bunnyroom.roomRegister.model.HolidayDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRoomDAO {

    BusinessAllInfoDTO searchBusinessInfo(int businessNo);

    List<RoomDTO> searchRoomInBusiness(int businessNo);

    List<ClosedDayDTO> searchClosedDayInBusiness(int businessNo);

    List<HolidayDTO> searchHolidayInBusiness(int businessNo);
}
