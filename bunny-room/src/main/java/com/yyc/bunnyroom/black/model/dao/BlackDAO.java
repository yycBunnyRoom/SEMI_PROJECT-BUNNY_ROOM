package com.yyc.bunnyroom.black.model.dao;

import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlackDAO {

    List<BlackDTO> showAll();

    int addBlacklist(@Param("userNo") int userNo, @Param("auth") String auth, @Param("reason") String reason,
                     @Param("registDate") String registDate, @Param("sentenceTime") String sentenceTime);

    int modifyBlacklist(@Param("userNo") int userNo, @Param("reason") String reason,
                        @Param("updateDate") String updateDate, @Param("sentenceTime") String sentenceTime);

    Object searchBlackByEmail(@Param("email") String email);

    List<BlackDTO> showBlacklistByEmail(@Param("param") String param);

    List<BlackDTO> showBlacklistByNickname(@Param("param") String param);

    List<BlackDTO> showBlacklistByPhone(@Param("param") String param);

    int disableBlack(@Param("userNo") int userNo, @Param("updateDate") String updateDate);

    List<BlackDTO> showBlacklistByInactive();

    Object searchBlackByEmailInAny(@Param("email") String email);
}
