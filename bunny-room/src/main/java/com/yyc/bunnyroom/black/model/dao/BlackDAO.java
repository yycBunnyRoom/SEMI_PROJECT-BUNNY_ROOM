package com.yyc.bunnyroom.black.model.dao;

import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlackDAO {

    int toBlacklist(String email);

    List<BlackDTO> showAll();

    int addBlacklist(@Param("userNo") int userNo, @Param("auth") String auth, @Param("reason") String reason, @Param("registDate") String registDate, @Param("sentenceTime") String sentenceTime);

    int modifyBlacklist(int userNo, String auth, String reason, String updateDate, String sentenceTime);

    Object searchBlackByEmail(String email);

    List<BlackDTO> showBlacklistByEmail(String param);

    List<BlackDTO> showBlacklistByNickname(String param);

    List<BlackDTO> showBlacklistByPhone(String param);

    int restoreAuth(@Param("email") String email, @Param("auth") String auth);

    int disableBlack(@Param("userNo") int userNo, @Param("updateDate") String updateDate);
}
