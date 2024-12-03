package com.example.shopproj.repository;

import com.example.shopproj.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // 사용자의 email을 통해서 장바구니를 찾는다.
    // Member member = findByEmail(email);
    // cartRepository.findByMemberId(member.id)

    public Cart findByMemberId(Long memberId);
    public Cart findByMemberEmail(String email);
}
