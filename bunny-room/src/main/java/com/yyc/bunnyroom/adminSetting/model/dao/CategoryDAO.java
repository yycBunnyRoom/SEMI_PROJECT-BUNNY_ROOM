package com.yyc.bunnyroom.adminSetting.model.dao;

import com.yyc.bunnyroom.adminSetting.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDAO {
    List<CategoryDTO> showCategory();

    int changeCategory(@Param("categoryNo") int categoryNo, @Param("categoryName") String categoryName, @Param("colorCode") String colorCode);

    int offCategory(@Param("categoryNo") int categoryNo);
}
