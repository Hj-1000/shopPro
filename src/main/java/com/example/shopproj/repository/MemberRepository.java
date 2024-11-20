package com.example.shopproj.repository;

import com.example.shopproj.entity.Item;
import com.example.shopproj.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 로그인 한 유저 정보 email을 통해서 각종 검색을 위한 기능
    // 등록시에도 사용
    // 로그인한 사람의 아이템에서도 사용
    // 등록한 글을 수정할 때 로그인 한 사람의 정보를 받아와서 글 등록한 사람과
    // 비교시에도 사용
    // 글을 댓글을 리뷰를 아이템을 삭제할 때도 같아야 삭제하는데
    // 같은지 알려면 먼저 로그인한 사람의 정보를 받아와야함으로 필요하다.
    Member findByEmail(String email);

}
