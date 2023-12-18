package com.yyc.bunnyroom.black.service;

import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import com.yyc.bunnyroom.black.model.dao.BlackDAO;
import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackService {

    @Autowired
    private BlackDAO blackDAO;

    public List<BlackDTO> showAll() {

        List<BlackDTO> blacklist = blackDAO.showAll();

        return blacklist;
    }

    public List<BlackDTO> showBlacklist(String str) {
        String param = "%" + str + "%";
        List<BlackDTO> blacklist;

        if(isNumeric(str)) {
            blacklist = blackDAO.showBlacklistByInt(str);
            return blacklist;
        }else {
            blacklist = blackDAO.showBlacklistByString(param);
            return blacklist;
        }
    }

    public int addBlacklist(String email) {

        int result = blackDAO.addBlacklist(email);

        if(result > 0){
            System.out.println("정상적으로 블랙처리되었습니다.");
            return result;
        }else {
            System.out.println("정상적으로 처리되지 않았습니다.");
            return 0;
        }
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


}
