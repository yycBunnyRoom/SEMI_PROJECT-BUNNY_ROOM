package com.yyc.bunnyroom.common.imgUploader.model.dto;

import lombok.*;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageDTO {

    private String originalName;

    private String changedName;

    private String ext;

    private String Path;

    private String register;

    private String update;

    private String status;
}
