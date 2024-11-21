package com.example.shopproj.dto;

import com.example.shopproj.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
//allArgs~는 builder안쓸거라 안쓸래
public class ItemImgDTO {
    private Long id;

    private String imgName; // 이미지 파일명

    private String oriImgName;  // 원본 이미지명

    private String imgUrl;  // 이미지 조회 경로

    private String repimgYn;    // 대표이미지 여부

    private Item item;


}
