package com.example.shopproj.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO {

    private Long cartItemId;      // 장바구니 상품 아이디

    private String itemNm;      // 상품명

    private int price;

    private int count;

    private String imgUrl;

    // 생성자
    // @AllArgsConstructor 쓰면 아래꺼 안써도 된다
//    public CartDetailDTO (Long cartItemId, String itemNm,
//                          int price, int count, String imgUrl){
//        this.cartItemId = cartItemId;
//        this.itemNm = itemNm;
//        this.price = price;
//        this.count = count;
//        this.imgUrl = imgUrl;
//    }

}
