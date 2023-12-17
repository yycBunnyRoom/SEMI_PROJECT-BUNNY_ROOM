package com.yyc.bunnyroom.admin.service;

import com.yyc.bunnyroom.admin.model.dao.AdminDAO;
import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    public List<MemberDTO> searchAllMember() {
        // 전체 회원 정보를 탐색
        List<MemberDTO> members =adminDAO.searchAllMember();

        return members;
    }

    public List<MemberDTO> searchMember(String str) {
        String param = "%" + str + "%";
        List<MemberDTO> members;

        if(isNumeric(str)){
            // 입력값이 숫자인 경우
            members = adminDAO.searchAllMemberByInt(Integer.parseInt(str));
        }else {
            // 입력값이 문자인 경우
            members =adminDAO.searchAllMemberByString(param);
        }
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
}
