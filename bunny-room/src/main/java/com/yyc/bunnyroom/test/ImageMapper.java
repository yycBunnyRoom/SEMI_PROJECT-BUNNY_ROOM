package com.yyc.bunnyroom.test;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    int insertImage(ImageEntity imageEntity);

    ImageEntity getImageById(int id);
}
