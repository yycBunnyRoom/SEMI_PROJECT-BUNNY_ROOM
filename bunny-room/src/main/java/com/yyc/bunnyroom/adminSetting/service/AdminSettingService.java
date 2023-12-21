package com.yyc.bunnyroom.adminSetting.service;

import com.yyc.bunnyroom.adminSetting.model.dao.CategoryDAO;
import com.yyc.bunnyroom.adminSetting.model.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSettingService {

    private final CategoryDAO categoryDAO;

    /**
     * 전체 카테고리를 조회해주는 메소드*/
    public List<CategoryDTO> showCategory() {

        List<CategoryDTO> categorylist = categoryDAO.showCategory();

        return categorylist;
    }
}
