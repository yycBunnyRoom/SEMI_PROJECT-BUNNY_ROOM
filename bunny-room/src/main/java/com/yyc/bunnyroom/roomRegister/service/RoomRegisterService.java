package com.yyc.bunnyroom.roomRegister.service;

import com.yyc.bunnyroom.roomRegister.dao.RoomRegisterMapper;
import com.yyc.bunnyroom.roomRegister.model.BusinessCategoryDTO;
import com.yyc.bunnyroom.roomRegister.model.BusinessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomRegisterService {

    @Autowired
    RoomRegisterMapper roomRegisterMapper;

    // 사업체 등록
    public int businessRegister(BusinessDTO businessDTO) {
        return roomRegisterMapper.businessRegister(businessDTO);
    }

    public List<BusinessCategoryDTO> selectAllBusinessCategory() {
        return roomRegisterMapper.selectAllBusinessCategory();
    }
}
