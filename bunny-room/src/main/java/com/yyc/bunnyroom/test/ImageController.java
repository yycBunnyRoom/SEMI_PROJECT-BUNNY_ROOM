package com.yyc.bunnyroom.test;

import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private RoomRegisterService roomRegisterService;

    @PostMapping("/upload")
    public ResponseEntity<Integer> receivedUploadImageRequest(@RequestParam("file") MultipartFile file, @RequestParam("roomNo") Integer roomNo) throws IOException {

        // 이미지 업로드가 성공한다면 해당 이미지의 idx
        // 이미지가 실패한다면 0 반환
        int imageNo = uploadImage(file,roomNo);

        if (imageNo>0){
            return ResponseEntity.ok(imageNo);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }

    }

    @GetMapping("/download/{roomNo}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable int roomNo) throws IOException {
        int imageNo = roomRegisterService.getImageByRoomNo(roomNo);
        byte[] imageData = imageService.getImageById(imageNo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }


    // 이미지를 업로드하는 메소드
    // 이미지 파일(file) 과 방 번호(roomNo)를 입력 받으면
    // 먼저 이미지를 저장하고 방 DB에 해당하는 이미지 번호와 방번호를 1대1 매칭시킨다
    // 업로드 성공하면 해당 이미지의 idx를 반환하고
    // 실패하면 0을 반환한다
    public int uploadImage(MultipartFile file, Integer roomNo) throws IOException {
        byte[] imageData = file.getBytes();

        ImageEntity image = new ImageEntity();
        image.setImageData(imageData);

        try {
            imageService.saveImageToDatabase(image);

            Integer imageNo = image.getId();

            if (roomNo != null){
                int result = roomRegisterService.updateImage(roomNo,imageNo);
                if (result > 0){
                    return imageNo;
                }
            }
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
