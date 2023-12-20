package com.yyc.bunnyroom.black.model.dao;

import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlackDAO {

    int toBlacklist(@Param("email") String email);

    List<BlackDTO> showAll();

    int addBlacklist(@Param("userNo") int userNo, @Param("auth") String auth, @Param("reason") String reason, @Param("registDate") String registDate, @Param("sentenceTime") String sentenceTime);

    int modifyBlacklist(@Param("userNo") int userNo, @Param("auth") String auth, @Param("reason") String reason, @Param("updateDate") String updateDate, @Param("sentenceTime") String sentenceTime);

    Object searchBlackByEmail(@Param("email") String email);

    List<BlackDTO> showBlacklistByEmail(@Param("param") String param);

    List<BlackDTO> showBlacklistByNickname(@Param("param") String param);

    List<BlackDTO> showBlacklistByPhone(@Param("param") String param);

    int restoreAuth(@Param("email") String email, @Param("auth") String auth);

    int disableBlack(@Param("userNo") int userNo, @Param("updateDate") String updateDate);
}
