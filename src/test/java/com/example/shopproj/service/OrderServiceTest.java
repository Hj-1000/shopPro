package com.example.shopproj.service;


import com.example.shopproj.dto.OrderHistDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    public void  getOrderListTest(){
        Pageable pageable = PageRequest.of(0, 30);

        Page<OrderHistDTO> orderHistDTOPage =
        orderService.getOrderList("sin@a.a",pageable);

        log.info("총 게시물 수는 : " + orderHistDTOPage.getTotalElements());
        log.info("dtoList : " + orderHistDTOPage.getContent());

        orderHistDTOPage.getContent().forEach(a->log.info(a));

    }

}