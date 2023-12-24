package com.yyc.bunnyroom.adminBusiness.model.dao;

import com.yyc.bunnyroom.adminBusiness.model.dto.AdminBusinessDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminBusinessDAO {
    List<AdminBusinessDTO> allSearcher();
}
