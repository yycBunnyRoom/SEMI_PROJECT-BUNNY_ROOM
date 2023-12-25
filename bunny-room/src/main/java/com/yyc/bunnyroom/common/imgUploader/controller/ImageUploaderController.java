package com.yyc.bunnyroom.common.imgUploader.controller;

import com.yyc.bunnyroom.common.imgUploader.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ImageUploaderController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/image")
    public String imageTest(){
        return "/test/imgTest/image";
    }

    @PostMapping("/imageUpload")
    public String imageUploader(@RequestParam(name = "img") List<MultipartFile> images, @RequestParam(name = "mode") String mode, Model model) throws IOException {
        // 이미지가 저장된 경로를 탐색
        // 프로필 이미지
        Resource profile = resourceLoader.getResource("classpath:static/img/profile");
        String profilePath;

        // 공지사항 이미지
        Resource announcement = resourceLoader.getResource("classpath:static/img/announcement");
        String announcementPath;

        // 문의사항 이미지
        Resource inquiry = resourceLoader.getResource("classpath:static/img/inquiry");
        String inquiryPath;

        // 리뷰 이미지
        Resource review = resourceLoader.getResource("classpath:static/img/review");
        String reviewPath;

        // 방 이미지
        Resource room = resourceLoader.getResource("classpath:static/img/room");
        String roomPath;

        // 경로가 없다면 경로를 생성
        // 프로필
        if(!profile.exists()){
            String root = "src/main/resources/static/img/profile";
            File file = new File(root);
            file.mkdirs();

            profilePath = file.getAbsolutePath();
        }else {
            profilePath = resourceLoader.getResource("classpath:static/img/profile").getFile().getAbsolutePath();
        }

        // 공지사항
        if(!announcement.exists()){
            String root = "src/main/resources/static/img/announcement";
            File file = new File(root);
            file.mkdirs();

            announcementPath = file.getAbsolutePath();
        }else {
            announcementPath = resourceLoader.getResource("classpath:static/img/announcement").getFile().getAbsolutePath();
        }

        // 문의사항
        if(!inquiry.exists()){
            String root = "src/main/resources/static/img/inquiry";
            File file = new File(root);
            file.mkdirs();

            inquiryPath = file.getAbsolutePath();
        }else {
            inquiryPath = resourceLoader.getResource("classpath:static/img/inquiry").getFile().getAbsolutePath();
        }

        // 리뷰
        if(!review.exists()){
            String root = "src/main/resources/static/img/review";
            File file = new File(root);
            file.mkdirs();

            reviewPath = file.getAbsolutePath();
        }else {
            reviewPath = resourceLoader.getResource("classpath:static/img/review").getFile().getAbsolutePath();
        }

        // 방
        if(!room.exists()){
            String root = "src/main/resources/static/img/room";
            File file = new File(root);
            file.mkdirs();

            roomPath = file.getAbsolutePath();
        }else {
            roomPath = resourceLoader.getResource("classpath:static/img/room").getFile().getAbsolutePath();
        }

        // 위까지가 경로 만들기, 아래부터 파일 저장

        List<ImageDTO> imageList = new ArrayList<>();

        // 임시로 출력할 이미지의 경로를 담음
        List<String> savedList = new ArrayList<>();

        try {
            for(MultipartFile image : images){
                // 이미지명 변경
                String originalName = image.getOriginalFilename();
                String ext = originalName.substring(originalName.lastIndexOf("."));
                String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

                // 이미지에 관한 정보 추출 후 보관과 저장
                // 프로필
                if(mode.equals("profile")) {
                    // 보관
                    imageList.add(new ImageDTO(originalName, savedName, profilePath));
                    // 저장
                    image.transferTo(new File(profilePath + "/" + savedName));
                    savedList.add("static/img/profile/" + savedName);

                } else if(mode.equals("announcement")){
                    // 보관
                    imageList.add(new ImageDTO(originalName, savedName, announcementPath));
                    // 저장
                    image.transferTo(new File(announcementPath + "/" + savedName));
                    savedList.add("static/img/announcement/" + savedName);
                } else if(mode.equals("inquiry")){
                    // 보관
                    imageList.add(new ImageDTO(originalName, savedName, inquiryPath));
                    // 저장
                    image.transferTo(new File(inquiryPath + "/" + savedName));
                    savedList.add("static/img/inquiry/" + savedName);
                }else if(mode.equals("review")){
                    // 보관
                    imageList.add(new ImageDTO(originalName, savedName, reviewPath));
                    // 저장
                    image.transferTo(new File(reviewPath + "/" + savedName));
                    savedList.add("static/img/review/" + savedName);
                }else if(mode.equals("room")){
                    // 보관
                    imageList.add(new ImageDTO(originalName, savedName, roomPath));
                    // 저장
                    image.transferTo(new File(roomPath + "/" + savedName));
                    savedList.add("static/img/room/" + savedName);
                }
            }

            model.addAttribute("message", "파일 업로드");
            model.addAttribute("img", savedList);
        }catch (Exception e){

            e.printStackTrace();

            // 실패시 삭제
            for(ImageDTO image : imageList){
                if(mode.equals("profile")){
                    new File(profilePath + "/" + image.getSavedName()).delete();
                }
            }
            model.addAttribute("message", "실패!");

        }
        
        return "/test/imgTest/image";
    }
}
