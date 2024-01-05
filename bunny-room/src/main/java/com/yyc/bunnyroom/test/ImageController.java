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
    public ResponseEntity<Integer> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("roomNo") Integer roomNo) throws IOException {

        byte[] imageData = file.getBytes();

        ImageEntity image = new ImageEntity();
        image.setImageData(imageData);


        try {
            imageService.saveImageToDatabase(image);

            Integer imageNo = image.getId();

            if (roomNo != null){
                int result = roomRegisterService.updateImage(roomNo,imageNo);
                if (result > 0){
                    return ResponseEntity.ok(imageNo);
                }

            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }

    @GetMapping("/download/{roomNo}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable int roomNo) throws IOException {

        System.out.println("입력 받은 방 번호: "+roomNo);

        int imageNo = roomRegisterService.getImageByRoomNo(roomNo);


        System.out.println("조회한 이미지 번호: "+imageNo);

        byte[] imageData = imageService.getImageById(imageNo);

        System.out.println(Arrays.toString(imageData));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }


}
