package com.yyc.bunnyroom.admin.model.dao;

import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDAO {
    List<MemberDTO> searchAllMember();

    int withdrawMember(String email);

    List<MemberDTO> searchMemberByEmail(String param);

    List<MemberDTO> searchMemberByNickname(String param);

    List<MemberDTO> searchMemberByPhone(String param);
}
