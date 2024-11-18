package com.example.shopproj.entity;

import com.example.shopproj.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.ParagraphView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @Column(name = "item_id")       // 테이블에서 매핑될 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품코드

    @Column(nullable = false, length = 50)
    private String itemNm; // 상품명

    @Column(name = "price", nullable = false)
    private int price;      // 가격

    private int stockNumber;    // 재고수량

    @Lob                    // 텍스트 많이
    @Column(nullable = false)
    private String itemDetail;  // 상품 상세설명
    // 상품 판매 상태
    @Enumerated(EnumType.STRING)        //enum 가지고 만듬 YES/NO, SELL/SOLD_OUT
    private ItemSellStatus itemSellStatus;  // 상품 판매 상태

    private LocalDateTime regTime;      //상품등록시간
    private LocalDateTime updateTime;   //상품수정시간


}