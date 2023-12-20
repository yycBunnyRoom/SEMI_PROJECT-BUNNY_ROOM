package com.yyc.bunnyroom.black.service;

import com.yyc.bunnyroom.black.model.dao.BlackDAO;
import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlackService {

    private final BlackDAO blackDAO;

    public List<BlackDTO> showAll() { // 전체검색

        List<BlackDTO> blacklist = blackDAO.showAll();

        return blacklist;
    }

    /**
     * 회원의 권한을 블랙리스트로 변경하는 메소드
     * */
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

    /** 입력받은 회원 정보에서 필요한 정보를 추출하여 블랙리스트 정보에 담는 메소드
     * */
    public int addBlacklist(int userNo, String auth, String email, String reason) {

        String registDate = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 3일의 블랙 기간
        String sentenceTime = ZonedDateTime.now().plusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        blackTimeout(email, auth);

        int result = blackDAO.addBlacklist(userNo, auth, reason, registDate, sentenceTime);

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
    public void blackTimeout(String email, String auth){
        restoreAuth(email, auth);
    }

    /**
     * 이미 블랙리스트에 오른 적이 있다면 기존 정보를 수정해 재등록하는 메소드
     * */
    public int modifyBlacklist(int userNo, String auth, String email, String reason) {
        String updateDate = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String sentenceTime = ZonedDateTime.now().plusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        blackTimeout(email, auth);
        int result = blackDAO.modifyBlacklist(userNo, auth, reason, updateDate, sentenceTime);

        if(result > 0){
            return result;
        }else {
            return 0;
        }
    }

    /**
     * 특정 이메일로 블랙리스트를 조회하는 메소드
     * */
    public Object searchBlackByEmail(String email) {
        Object blackUser = blackDAO.searchBlackByEmail(email);
        return blackUser;
    }

    /**
     * 해당 단어를 포함한 이메일로 블랙리스트를 조회하는 메소드
     * */
    public List<BlackDTO> showBlacklistByEmail(String str) {
        String param = "%" + str + "%";
        List<BlackDTO> blacklist = blackDAO.showBlacklistByEmail(param);
        return blacklist;
    }

    /**
     * 해당 단어를 포함한 닉네임으로 블랙리스트를 조회하는 메소드
     * */
    public List<BlackDTO> showBlacklistByNickname(String str) {
        String param = "%" + str + "%";
        List<BlackDTO> blacklist = blackDAO.showBlacklistByNickname(param);
        return blacklist;
    }

    /**
     * 해당 숫자를 포함한 연락처로 블랙리스트를 조회하는 메소드
     * */
    public List<BlackDTO> showBlacklistByPhone(String str) {
        String param = "%" + str + "%";
        List<BlackDTO> blacklist = blackDAO.showBlacklistByPhone(param);
        return blacklist;
    }

    /**
     * 블랙리스트 권한을 원상복구하는 요청을 수행하는 메소드
     * */
    public int restoreAuth(String email, String auth) {
        int result = blackDAO.restoreAuth(email, auth);


        if(result > 0){
            return result;
        }else {
            return 0;
        }
    }

    /**
     * 블랙리스트 명단에서 비활성화시키는 메소드
     * */
    public int disableBlack(int userNo) {
        String updateDate = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int result = blackDAO.disableBlack(userNo, updateDate);

        if(result > 0){
            return result;
        }else {
            return 0;
        }
    }
}
