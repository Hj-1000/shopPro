package com.example.shopproj.entity;


import com.example.shopproj.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString (exclude = "order")  //(exclude = "itemImgList")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_item")
public class OrderItem extends BaseTimeEntity {

    @Id
    @Column(name = "order_item_id")   // 테이블에서 매핑될 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)   // 회원 엔티티와 일대일로 매핑
    @JoinColumn(name = "order_id") // 매핑할 외래키 지정
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)   // 회원 엔티티와 일대일로 매핑
    @JoinColumn(name = "item_id") // 매핑할 외래키 지정
    private Item item;

    private int orderPrice; // 주문가격

    private int count;  // 수량



    // name 속성에는 매핑할 외래키의 이름을 설정합니다.
    // 설정하지 않으면 jpa 알아서 id를 찾지만 컬럼명이 원하는대로
    // 생성되지 않을 수 있기 때문


}
