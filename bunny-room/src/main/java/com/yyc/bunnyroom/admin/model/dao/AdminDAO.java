package com.yyc.bunnyroom.admin.model.dao;

import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminDAO {
    List<MemberDTO> searchAllMember();

    int withdrawMember(@Param("email") String email, @Param("reason")String reason, @Param("updateDate")String updateDate);

    List<MemberDTO> searchMemberByEmail(String param);

    List<MemberDTO> searchMemberByNickname(String param);

    List<MemberDTO> searchMemberByPhone(String param);

    List<MemberDTO> searchMemberByInactive();

    List<MemberDTO> searchMemberByInactiveFor(String email);

    MemberDTO searchAllConditionByEmail(String email);
}
