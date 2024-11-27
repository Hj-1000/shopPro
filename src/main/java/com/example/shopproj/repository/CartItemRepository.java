package com.example.shopproj.repository;

import com.example.shopproj.entity.Cart;
import com.example.shopproj.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
