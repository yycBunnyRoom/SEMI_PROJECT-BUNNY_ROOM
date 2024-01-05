package com.yyc.bunnyroom.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@RestController
public class ImageUploadController {

    @Autowired
    private ImageService imageService; // 이미지 서비스 인스턴스

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 이미지 서비스를 사용하여 이미지를 DB에 저장하고 이미지의 URL을 반환
            imageService.saveImageToDatabase(file);
            return ResponseEntity.ok("이미지 업로드 완료");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패");
        }
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) throws IOException {
        byte[] imageData = imageService.getImageById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 타입에 따라 변경

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }


}