package com.example.shopproj.service;

import com.example.shopproj.dto.CartItemDTO;
import com.example.shopproj.repository.CartItemRepository;
import com.example.shopproj.repository.CartRepository;
import com.example.shopproj.repository.ItemRepository;
import com.example.shopproj.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class CartServiceTest {

    @Autowired
    private  CartService cartService;


    @Test
    public void addCartTest(){
        // 회원, 들어갈 아이템id, 수량
        String email = "sin@a.a";

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemid(417L);
        cartItemDTO.setCount(8);

        Long result = cartService.addCart(cartItemDTO, email);
        log.info("저장된 장바구니 아이템의 번호는 ? " + result);

    }

}