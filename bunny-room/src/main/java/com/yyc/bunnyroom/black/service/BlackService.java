package com.yyc.bunnyroom.black.service;

import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import com.yyc.bunnyroom.black.model.dao.BlackDAO;
import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public int toBlacklist(String email) {

        int result = blackDAO.toBlacklist(email);

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

    /** 입력받은 회원 정보에서 필요한 정보를 추출하여 블랙리스트 정보에 담는 메소드
     * */
    public int addBlacklist(int userNo, String reason) {

        LocalDateTime registDate = LocalDateTime.now();
        // 3일의 블랙 기간
        LocalDateTime sentenceTime = LocalDateTime.now().plusDays(3);
        blackTimeout();

        int result = blackDAO.addBlacklist(userNo, reason, registDate, sentenceTime);

        if(result > 0) {
            return result;
        }else {
            return 0;
        }
    }

    /**
     * 블랙 기간이 지났을 때 자동으로 풀어주는 메소드
     * */
    @Scheduled(fixedDelay = 3 * 24 * 60 * 60 * 1000)
    public void blackTimeout(){

    }
}
