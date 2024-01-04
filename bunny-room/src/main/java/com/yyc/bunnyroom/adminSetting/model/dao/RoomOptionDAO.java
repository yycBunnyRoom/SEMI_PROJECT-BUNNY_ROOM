package com.yyc.bunnyroom.adminSetting.model.dao;

import com.yyc.bunnyroom.roomRegister.model.RoomOptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomOptionDAO {


    List<RoomOptionDTO> showOption();

    int changeOptionName(@Param("optionNo") int optionNo, @Param("newName") String newName);

    int addOption(String optionName);
}
