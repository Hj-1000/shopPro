package com.example.shopproj.repository.search;

import com.example.shopproj.dto.PageRequestDTO;
import com.example.shopproj.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface ItemSearchRepository {
    // 검색과 페이징처리를 하는 목록기능

//    Pageable a = PageRequest.of(0, 10, Sort.by("정렬값"));


    Page<Item> getAdminItemPage(PageRequestDTO pageRequestDTO, Pageable pageable, String email);





}
