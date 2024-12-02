package com.example.shopproj.repository;

import com.example.shopproj.entity.Cart;
import com.example.shopproj.entity.CartItem;
import com.example.shopproj.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    //구매이력

    public List<OrderItem> findByOrderId (Long itemid);

}
