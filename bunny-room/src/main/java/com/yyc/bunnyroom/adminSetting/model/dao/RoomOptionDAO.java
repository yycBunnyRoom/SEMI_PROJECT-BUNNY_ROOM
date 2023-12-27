package com.yyc.bunnyroom.adminSetting.model.dao;

import com.yyc.bunnyroom.roomRegister.model.RoomOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomOptionDAO {


    List<RoomOptionDTO> showOption();
}
