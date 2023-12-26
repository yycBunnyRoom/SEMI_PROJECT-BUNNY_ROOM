package com.yyc.bunnyroom.common.imgUploader.service;

import com.yyc.bunnyroom.common.imgUploader.model.dao.ImageDAO;
import com.yyc.bunnyroom.common.imgUploader.model.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ImageService {

    @Autowired
    private ImageDAO imageDAO;

    /**
     * 프로필 이미지를 DB에 저장하는 메소드
     * */
    public int inputProfileImage(String originalName, String changedName, String ext, String path) {
        String register = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        int result = imageDAO.inputProfileImage(originalName, changedName, ext, path, register);

        if(result > 0){
            return result;
        }else {
            return 0;
        }

    }

    /**
     * 프로필 이미지를 조회하는 메소드
     * */
    public ImageDTO getProfileImage(int imageNo) {

        ImageDTO image = imageDAO.getProfileImage(imageNo);

        return image;
    }
}
