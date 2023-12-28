package com.yyc.bunnyroom.inquiry.controller;

import com.yyc.bunnyroom.inquiry.dto.InquiryDTO;
import com.yyc.bunnyroom.inquiry.service.InquiryService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class InquiryInterceptor implements HandlerInterceptor {


    @Autowired
    private InquiryService inquiryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // /updateInquiry 경로에서만 동작하도록 설정
        if (request.getRequestURI().startsWith("/updateInquiry")) {
            // inquiryNo를 파라미터에서 추출
            int inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));

            // 현재 사용자의 정보를 가져옴
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();

                // 글 작성자의 정보를 가져옴
                InquiryDTO originalInquiry = inquiryService.searchInquiry(inquiryNo);

                // 현재 사용자가 글 작성자인지 확인
                if (userDetails.getLoginUserDTO().getUserNo() != originalInquiry.getUserNo()) {
                    // 권한이 없는 경우 에러 처리 또는 리다이렉트 등을 수행
                    response.sendRedirect("/inquirys");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
