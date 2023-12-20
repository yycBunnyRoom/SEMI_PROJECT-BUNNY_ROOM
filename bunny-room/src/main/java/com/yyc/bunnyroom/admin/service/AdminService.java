package com.yyc.bunnyroom.admin.service;

import com.yyc.bunnyroom.admin.model.dao.AdminDAO;
import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminDAO adminDAO;

    public List<MemberDTO> searchAllMember() {
        // 전체 회원 정보를 탐색
        List<MemberDTO> members =adminDAO.searchAllMember();

        return members;
    }

    /**
     * 입력받은 문자열이 숫자인지 확인하는 메소드
     * */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 해당회원의 탈퇴 요청을 수행하는 메소드
     * */
    public int withdrawMember(String email) {

        int result = adminDAO.withdrawMember(email);

        if(result > 0){
            System.out.println("정상적으로 탈퇴처리되었습니다.");
            return result;
        }else {
            System.out.println("정상적으로 처리되지 않았습니다.");
            return 0;
        }
    }

    /**
     * 이메일을 조건으로 회원을 조회하는 메소드
     * */
    public List<MemberDTO> searchMemberByEmail(String str) {
        String param = "%" + str + "%";
        List<MemberDTO> members = adminDAO.searchMemberByEmail(param);
        return members;
    }

    /**
     * 닉네임을 조건으로 회원을 조회하는 메소드
     * */
    public List<MemberDTO> searchMemberByNickname(String str) {
        String param = "%" + str + "%";
        List<MemberDTO> members = adminDAO.searchMemberByNickname(param);
        return members;
    }

    /**
     * 연락처를 조건으로 회원을 조회하는 메소드
     * */
    public List<MemberDTO> searchMemberByPhone(String str) {
        String param = "%" + str + "%";
        List<MemberDTO> members = adminDAO.searchMemberByPhone(param);
        return members;
    }
}
