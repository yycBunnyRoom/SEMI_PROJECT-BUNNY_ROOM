package com.yyc.bunnyroom.adminBusiness.service;

import com.yyc.bunnyroom.adminBusiness.model.dao.AdminBusinessDAO;
import com.yyc.bunnyroom.adminBusiness.model.dto.AdminBusinessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminBusinessService {

    private final AdminBusinessDAO adminBusinessDAO;

    /**
     * 사업체 전체 검색을 위한 메소드
     * */
    public List<AdminBusinessDTO> allSearcher() {
        List<AdminBusinessDTO> businessList = adminBusinessDAO.allSearcher();
        return businessList;
    }
}
