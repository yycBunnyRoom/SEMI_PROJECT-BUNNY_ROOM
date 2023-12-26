/*
package com.yyc.bunnyroom.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // ZoneDateTime 을 매핑 시킬 수 있게 설정
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        registry.addConverter(String.class, ZonedDateTime.class,
                source -> ZonedDateTime.parse(source, formatter));
    }
}*/
