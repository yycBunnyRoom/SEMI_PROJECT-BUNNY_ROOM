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
     * 전체 카테고리를 조회해주는 메소드
     * */
    public List<CategoryDTO> showCategory() {

        List<CategoryDTO> categorylist = categoryDAO.showCategory();

        return categorylist;
    }

    /**
     * 카테고리 정보를 DAO로 넘겨 변경을 시도하는 메소드
     * */
    public int changeCategory(int categoryNo, String categoryName, String colorCode) {

        int result = categoryDAO.changeCategory(categoryNo, categoryName, colorCode);

        if(result > 0){
            return result;
        }else {
            return 0;
        }
    }

    public int offCategory(int categoryNo) {
        int result= categoryDAO.offCategory(categoryNo);

        return result;
    }
}
