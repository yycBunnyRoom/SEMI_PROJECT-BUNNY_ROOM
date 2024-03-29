package com.yyc.bunnyroom.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;


    public int saveImageToDatabase(ImageEntity image) throws IOException {
        return imageMapper.insertImage(image);
    }

    public byte[] getImageById(int id) throws IOException {
        ImageEntity imageEntity = imageMapper.getImageById(id);
        return imageEntity.getImageData();
    }
}

