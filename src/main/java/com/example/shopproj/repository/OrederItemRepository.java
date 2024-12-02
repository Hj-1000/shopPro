package com.example.shopproj.repository;

import com.example.shopproj.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

// OderItem과  Order를 서로 단방향으로 쓸 경우 필요한 리포지토리 -> 서비스 까지만들거임
public interface OrederItemRepository extends JpaRepository<OrderItem, Long> {
}
