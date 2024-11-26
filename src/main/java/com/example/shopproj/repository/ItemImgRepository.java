package com.example.shopproj.repository;

import com.example.shopproj.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    public List<ItemImg> findByItemId(Long id);

    public ItemImg findByItemIdAndRepimgYn(Long id, String val);
}
