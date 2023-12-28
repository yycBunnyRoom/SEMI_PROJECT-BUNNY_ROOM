package com.yyc.bunnyroom.adminBusiness.service;

import com.yyc.bunnyroom.adminBusiness.model.dao.AdminRoomDAO;
import com.yyc.bunnyroom.adminBusiness.model.dto.BusinessAllInfoDTO;
import com.yyc.bunnyroom.roomRegister.model.ClosedDayDTO;
import com.yyc.bunnyroom.roomRegister.model.HolidayDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRoomService {

    private final AdminRoomDAO adminRoomDAO;

    /**
     * 해당 업체에 대한 상세한 정보를 가져오는 메소드
     * */
    public BusinessAllInfoDTO searchBusinessInfo(int businessNo) {
        BusinessAllInfoDTO businessInfo = adminRoomDAO.searchBusinessInfo(businessNo);
        return businessInfo;
    }

    /**
     * 사업체 번호를 조건으로 방 정보를 조회하는 메소드
     * */
    public List<RoomDTO> searchRoomInBusiness(int businessNo) {
        List<RoomDTO> roomInBusinessInfo = adminRoomDAO.searchRoomInBusiness(businessNo);
        return roomInBusinessInfo;
    }

    /**
     * 사업체 번호를 조건으로 업체의 휴일을 조회하는 메소드
     * */
    public List<ClosedDayDTO> searchClosedDayInBusiness(int businessNo) {
        List<ClosedDayDTO> closedDayInBusinessInfo = adminRoomDAO.searchClosedDayInBusiness(businessNo);
        return closedDayInBusinessInfo;
    }

    /**
     * 사업체 번호를 조건으로 업체의 비정기 휴일을 조회하는 메소드
     * */
    public List<HolidayDTO> searchHolidayInBusiness(int businessNo) {
        List<HolidayDTO> holidayInBusinessInfo = adminRoomDAO.searchHolidayInBusiness(businessNo);
        return holidayInBusinessInfo;
    }

    /**
     * 방 번호를 조건으로 해당 방을 삭제(비활성화)하는 메소드
     * */
    public int deleteRoom(int roomNo, String update) {
        int result = adminRoomDAO.deleteRoom(roomNo, update);

        if(result > 0){
            return result;
        }else {
            return 0;
        }
    }
}
