package com.yyc.bunnyroom.admin.model.dao;

import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDAO {
    List<MemberDTO> searchAllMember();

    List<MemberDTO> searchAllMemberByInt(int str);

    List<MemberDTO> searchAllMemberByString(String param);
}
