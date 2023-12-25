package com.yyc.bunnyroom.common.imgUploader.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageDTO {

    private String originalName;

    private String savedName;

    private String imagePath;
}
