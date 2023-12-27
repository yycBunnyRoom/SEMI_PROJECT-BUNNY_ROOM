package com.yyc.bunnyroom.roomRegister.service;

import com.yyc.bunnyroom.roomRegister.dao.RoomRegisterMapper;
import com.yyc.bunnyroom.roomRegister.model.*;
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

    public int closedDaysRegister(List<ClosedDayDTO> list) {
        return roomRegisterMapper.closedDaysRegister(list);
    }

    public int holidaysRegister(List<HolidayDTO> holidayList) {
        return roomRegisterMapper.holidaysRegister(holidayList);
    }


    // DB에 있는 방 옵션 전체를 불러온다
    public List<RoomOptionDTO> selectAllRoomOptions() {
        return roomRegisterMapper.selectAllRoomOptions();
    }

    // DB에 있는 회원의 사업체 전체를 불러온다
    public List<BusinessDTO> getAllBusiness(int ownerNo) {
        return roomRegisterMapper.getAllBusiness(ownerNo);
    }

    // 개별 사업체 상세내역을 보여주는 페이지
    public BusinessDTO getBusinessDetails(int businessNo) {
        return roomRegisterMapper.getBusinessDetails(businessNo);
    }
}
