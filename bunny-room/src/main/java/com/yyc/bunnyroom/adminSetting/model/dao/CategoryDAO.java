package com.yyc.bunnyroom.adminSetting.model.dao;

import com.yyc.bunnyroom.adminSetting.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDAO {
    List<CategoryDTO> showCategory();
}
