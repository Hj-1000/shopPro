package com.example.shopproj.repository;

import com.example.shopproj.dto.PageRequestDTO;
import com.example.shopproj.entity.Order;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    public void findOrderlistEmailTest(){
        // 에러나는 이유 현재 pagerable을 파라메터로 추가안해줌
        // repository 수정과정
        String email = "sin@a.a";


//        List<Order> orderList =
//        orderRepository.findByMemberEmailOrderByOrderDateDesc(email, pageable);
//        orderList.forEach(a -> log.info(a));
//        System.out.println("-----------------------");
//        System.out.println("-----------------------");
//        System.out.println("-----------------------");
//
//
//        List<Order> orderListAAA =
//                orderRepository.findOrders(email, pageable);
//        orderListAAA.forEach(a -> log.info(a));
    }
}