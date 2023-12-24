package com.yyc.bunnyroom.adminBusiness.model.dao;

import com.yyc.bunnyroom.adminBusiness.model.dto.AdminBusinessDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminBusinessDAO {
    List<AdminBusinessDTO> allSearcher();

    List<AdminBusinessDTO> nameSearcher(String param);

    List<AdminBusinessDTO> categorySearcher(String param);

    List<AdminBusinessDTO> registNoSearcher(String target);

    List<AdminBusinessDTO> emailSearcher(String param);

    List<AdminBusinessDTO> nicknameSearcher(String param);

    List<AdminBusinessDTO> addressSearcher(String param);

    List<AdminBusinessDTO> phoneSearcher(String param);

    List<AdminBusinessDTO> inactiveSearcher();
}
