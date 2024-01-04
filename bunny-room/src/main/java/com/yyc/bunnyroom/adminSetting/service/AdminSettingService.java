package com.yyc.bunnyroom.adminSetting.service;

import com.yyc.bunnyroom.adminSetting.model.dao.CategoryDAO;
import com.yyc.bunnyroom.adminSetting.model.dao.RoomOptionDAO;
import com.yyc.bunnyroom.adminSetting.model.dto.CategoryDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomOptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSettingService {

    private final CategoryDAO categoryDAO;

    private final RoomOptionDAO roomOptionDAO;

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

    /**
     * 카테고리의 상태를 비활성화시키는 메소드
     * */
    public int offCategory(int categoryNo) {
        int result= categoryDAO.offCategory(categoryNo);

        return result;
    }

    /**
     * 새 카테고리를 추가하는 메소드
     * */
    public int newCategory(String categoryName, String colorCode) {
        int result = categoryDAO.newCategory(categoryName, colorCode);

        if(result > 0){
            return result;
        }else {
            return 0;
        }
    }

    /**
     * 모든 방 옵션을 조회하는 메소드
     * */
    public List<RoomOptionDTO> showOption() {
        List<RoomOptionDTO> optionList = roomOptionDAO.showOption();
        return optionList;
    }

    /**
     * 방 옵션명을 변경하는 서비스 메소드
     * */
    public int changeOptionName(int optionNo, String newName) {
        int result = roomOptionDAO.changeOptionName(optionNo, newName);

        if(result > 0){
            return result;
        }else {
            return 0;
        }
    }

    /**
     * 방 옵션을 추가하는 메소드
     * */
    public int addOption(String optionName) {
       int result = roomOptionDAO.addOption(optionName);

       if(result > 0){
           return result;
       }else {
           return 0;
       }
    }
}
