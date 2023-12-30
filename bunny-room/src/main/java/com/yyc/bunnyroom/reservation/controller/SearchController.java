package com.yyc.bunnyroom.reservation.controller;

import com.yyc.bunnyroom.reservation.model.KeywordDTO;
import com.yyc.bunnyroom.roomRegister.model.ClosedDayDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    RoomRegisterService roomRegisterService;

    @GetMapping("/category")
    public ModelAndView searchByCategory(@RequestParam(value = "businessCategoryName")String businessCategoryName,
                                           ModelAndView modelAndView){

        // 카테고리 이름으로 DB에서 해당하는 방 리스트를 가져옴
        List<RoomDTO> rooms = roomRegisterService.getRoomsByCategory(businessCategoryName);

        System.out.println("roomDTO가 들어왔는가? : "+rooms);



        if (Objects.isNull(rooms)){
            modelAndView.addObject("message", "해당 카테고리로 등록된 방이 없습니다.");
            modelAndView.setViewName("/main");
            return modelAndView;
        }
        else {
            modelAndView.addObject("rooms", rooms);
            modelAndView.setViewName("/reservation/view/searchResultView");
            return modelAndView;
        }

    }

    @GetMapping("/keyword")
    public ModelAndView searchByKeyword(@RequestParam(value = "keyword")String keyword,
                                         ModelAndView modelAndView) {

        String param = "%" + keyword + "%";

        KeywordDTO keywordDTO = new KeywordDTO();

        // KeywordDTO 클래스의 모든 필드에 접근
        Field[] fields = KeywordDTO.class.getDeclaredFields();

        // 각 필드에 값을 주입
        for (Field field : fields) {
            if (field.getType() == String.class) { // 필드가 String 타입인 경우에만 설정
                try {
                    field.setAccessible(true); // private 필드에 접근하기 위해 필요
                    field.set(keywordDTO, param); // 필드에 값을 설정
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // 카테고리 이름으로 DB에서 해당하는 방 리스트를 가져옴
        List<RoomDTO> rooms = roomRegisterService.getRoomsByKeyword(keywordDTO);

        System.out.println("roomDTO가 들어왔는가? : " + rooms);

        modelAndView.addObject("rooms", rooms);
        modelAndView.setViewName("/reservation/view/searchResultView");
        return modelAndView;

    }
}
