package com.yyc.bunnyroom.black.model.dao;

import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackDAO {

    int addBlacklist(String email);

    List<BlackDTO> showAll();

    List<BlackDTO> showBlacklistByString(String param);

    List<BlackDTO> showBlacklistByInt(String str);
}
