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




    //Register
    public Integer businessRegister(BusinessDTO businessDTO) {
        return roomRegisterMapper.businessRegister(businessDTO);
    }
    public int closedDaysRegister(List<ClosedDayDTO> list) {
        return roomRegisterMapper.closedDaysRegister(list);
    }

    public Integer roomRegister(RoomDTO newRoom) {
        Integer generatedRoomNo = roomRegisterMapper.roomRegister(newRoom);
        System.out.println("새로 생성된 방번호: "+generatedRoomNo);
        return generatedRoomNo;
    }

    public int holidaysRegister(List<HolidayDTO> holidayList) {
        return roomRegisterMapper.holidaysRegister(holidayList);
    }

    public Integer appliedOptionsRegister(List<AppliedOptionDTO> appliedOptions) {
        return roomRegisterMapper.appliedOptionsRegister(appliedOptions);
    }

    public Integer addTimeSchedule(List<TimeUnitScheduleDTO> timeUnitScheduleList) {
        return roomRegisterMapper.addTimeSchedule(timeUnitScheduleList);
    }




    //SelectAll
    public List<BusinessCategoryDTO> selectAllBusinessCategory() {
        return roomRegisterMapper.selectAllBusinessCategory();
    }

    public List<RoomOptionDTO> selectAllRoomOptions() {
        return roomRegisterMapper.selectAllRoomOptions();
    }

    public List<BusinessDTO> getAllBusiness(int ownerNo) {
        return roomRegisterMapper.getAllBusiness(ownerNo);
    }





    //Select
    public List<RoomDTO> getAllRooms(int businessNo) {
        System.out.println("service : "+businessNo);
        return roomRegisterMapper.getAllRooms(businessNo);
    }

    public List<AppliedOptionDTO> getAppliedOptions(int roomNo) {
        return roomRegisterMapper.getAppliedOptions(roomNo);
    }

    public BusinessDTO getBusinessDetails(int businessNo) {
        return roomRegisterMapper.getBusinessDetails(businessNo);
    }

    public RoomDTO getRoomDetails(int roomNo) {
        return roomRegisterMapper.getRoomDetails(roomNo);
    }



}
