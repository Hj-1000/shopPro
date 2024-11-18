package com.example.shopproj.repository;

import com.example.shopproj.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //제품명으로 검색 제품명은 동일한 이름이 있을 수 있으니
    // 여러개 출력 가능 List를 사용하겠다.

     public List<Item> findByItemNm (String itemNm);

     //동적 쿼리문
     @Query("select i from Item i where i.itemNm = :itemNm")
     public List<Item> selectWhereItemNm(String itemNm);

     // 제품명으로 검색, 비슷하면 맞게
    public List<Item> findByItemNmContaining(String itemNm);


    //동적쿼리문
    @Query("select i from Item i where i.itemNm like  concat('%',:itemNm,'%') ")
    public List<Item> selectWithNmLike(String itemNm);

    //상세설명으로 검색
    public List<Item> findByItemDetailContaining(String itemDetail);

    // 가격으로 검색
    public List<Item> findByPriceLessThan(Integer price);


    // 정렬까지 추가
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
}
