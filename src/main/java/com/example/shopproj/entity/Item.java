package com.example.shopproj.entity;

import com.example.shopproj.constant.ItemSellStatus;
import com.example.shopproj.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.ParagraphView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString   //(exclude = "itemImgList")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends BaseEntity {
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<ItemImg> itemImgList;


}
