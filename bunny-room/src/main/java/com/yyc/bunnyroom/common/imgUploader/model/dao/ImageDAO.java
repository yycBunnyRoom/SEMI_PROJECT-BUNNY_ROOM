package com.yyc.bunnyroom.common.imgUploader.model.dao;

import com.yyc.bunnyroom.common.imgUploader.model.dto.ImageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageDAO {
    int inputProfileImage(@Param("originalName") String originalName, @Param("changedName") String changedName, @Param("ext") String ext, @Param("path") String path, @Param("register") String register);

    ImageDTO getProfileImage(int imageNo);
}
