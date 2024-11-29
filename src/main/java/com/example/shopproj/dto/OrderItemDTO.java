package com.example.shopproj.dto;

import com.example.shopproj.entity.Item;
import com.example.shopproj.entity.Order;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;

    private OrderDTO orderDTO;

    private String itemNm;

    private int orderPrice; // 주문가격

    private int count;  // 수량

    private String imgUrl; //대표이미지
}
