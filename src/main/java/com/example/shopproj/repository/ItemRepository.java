package com.example.shopproj.repository;

import com.example.shopproj.entity.Item;
import com.example.shopproj.repository.search.ItemSearchRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> , ItemSearchRepository {
    //제품명으로 검색 제품명은 동일한 이름이 있을 수 있으니
    // 여러개 출력 가능 List를 사용하겠다.



     public List<Item> findByItemNm (String itemNm);

     public Item findByIdAndCreateBy(Long id, String email);

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

    // 미만으로 검색
    public List<Item> findByPriceLessThan(Integer price);
    // 이하으로 검색
    public List<Item> findByPriceLessThanEqual(Integer price);
    // 초과으로 검색
    public List<Item> findByPriceGreaterThan(Integer price);
    // 이상으로 검색



    public List<Item> findByPriceGreaterThanOrderByPriceAsc(Integer price);

    // 페이징처리된 초과이면서 같은거
    public List<Item> findByPriceGreaterThanEqual(Integer price, Pageable pageable);


    // 정렬까지 추가
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);


    //nativeQuery 사용
    @Query(value = "select * from Item i where i.item_Nm = :itemNm", nativeQuery = true)
    List<Item> nativeQuerySelectWhereNamelike(String itemNm, Pageable pageable);
}
