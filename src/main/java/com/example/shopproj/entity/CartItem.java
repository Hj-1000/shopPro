package com.example.shopproj.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString   //(exclude = "itemImgList")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart_item")
public class CartItem {

    @Id
    @Column(name = "cart_item_id")   // 테이블에서 매핑될 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)   // 회원 엔티티와 일대일로 매핑
    @JoinColumn(name = "cart_id") // 매핑할 외래키 지정
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)   // 회원 엔티티와 일대일로 매핑
    @JoinColumn(name = "item_id") // 매핑할 외래키 지정
    private Item item;

    // 수량 산수량 장바구니에 담긴 수량
    // 아이템 한 row 당
    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int count){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);

        return cartItem;
    }

    // count 증가
    public void addCount(int count){
        this.count += count;
    }

}
