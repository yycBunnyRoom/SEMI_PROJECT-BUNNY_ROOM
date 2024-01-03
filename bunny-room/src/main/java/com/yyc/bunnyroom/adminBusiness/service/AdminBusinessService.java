package com.yyc.bunnyroom.adminBusiness.service;

import com.yyc.bunnyroom.adminBusiness.model.dao.AdminBusinessDAO;
import com.yyc.bunnyroom.adminBusiness.model.dto.AdminBusinessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * 업체명으로 검색하는 메소드
     * */
    public List<AdminBusinessDTO> nameSearcher(String target) {
        String param = "%" + target + "%";
        List<AdminBusinessDTO> businessList = adminBusinessDAO.nameSearcher(param);
        return businessList;
    }

    /** 
     * 카테고리로 검색하는 메소드
     * */
    public List<AdminBusinessDTO> categorySearcher(String target) {
        String param = "%" + target + "%";
        List<AdminBusinessDTO> businessList = adminBusinessDAO.categorySearcher(param);
        return businessList;
    }

    /**
     * 사업자 번호를 통해 검색하는 메소드
     * */
    public List<AdminBusinessDTO> registNoSearcher(String target) {
        List<AdminBusinessDTO> businessList = adminBusinessDAO.registNoSearcher(target);
        return businessList;
    }

    /**
     * 이메일을 통해 검색하는 메소드
     * */
    public List<AdminBusinessDTO> emailSearcher(String target) {
        String param = "%" + target + "%";
        List<AdminBusinessDTO> businessList = adminBusinessDAO.emailSearcher(param);
        return businessList;
    }

    /**
     * 닉네임으로 검색하는 메소드
     * */
    public List<AdminBusinessDTO> nicknameSearcher(String target) {
        String param = "%" + target + "%";
        List<AdminBusinessDTO> businessList = adminBusinessDAO.nicknameSearcher(param);
        return businessList;
    }

    /**
     * 주소로 검색하는 메소드
     * */
    public List<AdminBusinessDTO> addressSearcher(String target) {
        String param = "%" + target + "%";
        List<AdminBusinessDTO> businessList = adminBusinessDAO.addressSearcher(param);
        return businessList;
    }

    /**
     * 연락처로 검색하는 메소드
     * */
    public List<AdminBusinessDTO> phoneSearcher(String target) {
        String param = "%" + target + "%";
        List<AdminBusinessDTO> businessList = adminBusinessDAO.phoneSearcher(param);
        return businessList;
    }

    /**
     * 삭제된 업체 검색하는 메소드
     * */
    public List<AdminBusinessDTO> inactiveSearcher() {
        List<AdminBusinessDTO> businessList = adminBusinessDAO.inactiveSearcher();
        return businessList;
    }

    /**
     * 업체를 삭제처리하는 메소드
     * */
    public int delete(int businessNo, String reason) {
        String update = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int result = adminBusinessDAO.delete(businessNo, reason, update);
        // 방 상태 또한 삭제처리
        int deleteRoom = adminBusinessDAO.deleteRoom(businessNo, update);

        if(result > 0 && deleteRoom > 0){
            System.out.println("정상적으로 DB에서 삭제되었습니다.");
            return result + deleteRoom;
        }else {
            return 0;
        }
    }

    /**
     * 업체를 복구시키는 메소드
     * */
    public int restore(int businessNo, String reason) {
        String update = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int result = adminBusinessDAO.restore(businessNo, update, reason);
        // 방 상태 또한 부활처리
        int restoreRoom = adminBusinessDAO.restoreRoom(businessNo, update);

        if(result > 0 && restoreRoom > 0){
            System.out.println("정상적으로 DB에서 복구되었습니다.");
            return result + restoreRoom;
        }else {
            return 0;
        }
    }
}
