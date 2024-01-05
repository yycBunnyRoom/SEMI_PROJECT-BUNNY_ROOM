//package com.yyc.bunnyroom.common.imgUploader.controller;
//
//import com.yyc.bunnyroom.common.imgUploader.model.dto.ImageDTO;
//import com.yyc.bunnyroom.common.imgUploader.service.ImageService;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@Controller
//public class ImageUploaderController {
//
//    @Autowired
//    private ImageService imageService;
//
//    @Autowired
//    private ResourceLoader resourceLoader;
//
////    @GetMapping("/image")
////    public String imageTest(){
////        return "/test/imgTest/image";
////    }
//
//    @PostMapping("/profileImage")
//    public String profileImageUploader(@RequestParam(name = "img") MultipartFile profileImage, Model model) throws IOException{
//        // 정적 리소스 디렉토리
//        String staticPath = "classpath:static/";
//
//        // 프로필 이미지 저장 디렉토리
//        String profilePath = "img/profile/";
//
//        // Resource를 통해 클래스패스 리소스를 가져옴
//        Resource resource = resourceLoader.getResource(staticPath);
//
//        // 저장 경로
//        String fullPath = resource.getFile().getAbsolutePath() + File.separator + profilePath;
//
//        // 저장 위치가 존재하지 않으면 생성
//        File directory = new File(fullPath);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        // 프로필 이미지명 변경
//        String originalName = profileImage.getOriginalFilename();
//        String ext = originalName.substring(originalName.lastIndexOf("."));
//        String changedName = UUID.randomUUID().toString().replace("-", "");
//        String savedName = changedName + ext;
//
//        // 프로필 이미지 저장
//        File imageFile = new File(fullPath + savedName);
//        profileImage.transferTo(imageFile);
//
//        int register = imageService.inputProfileImage(originalName, changedName, ext, profilePath + savedName);
//
//        if (register > 0) {
//            System.out.println("이미지 등록");
//            model.addAttribute("message", "프로필 이미지가 등록되었습니다.");
//        } else {
//            System.out.println("실패");
//            model.addAttribute("message", "프로필 이미지가 등록에 실패했습니다.");
//        }
//
//        return "/test/imgTest/image";
//    }
//
//    @GetMapping("/profileImage")
//    public String profileImage(@RequestParam(name = "imageNo") int imageNo, Model model){
//
//        ImageDTO image = imageService.getProfileImage(imageNo);
//
//        String path = image.getPath();
//        model.addAttribute("path", path);
//
//        return "/test/imgTest/image";
//    }
//
//}
