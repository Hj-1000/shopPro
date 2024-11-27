package com.example.shopproj.service;

import com.example.shopproj.dto.ItemDTO;
import com.example.shopproj.dto.PageRequestDTO;
import com.example.shopproj.dto.PageResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void remove(){
        Long testid = 415L;

        itemService.remove(testid);
    }



    @Test
    @DisplayName("페이징처리 테스트해보기")
    public void itempagelistTest(){
        // email, pageRequestDTO
        String email = "sin@a.a";

        PageRequestDTO pageRequestDTO =
                new PageRequestDTO();

        pageRequestDTO.setSearchDateType("6m");

        PageResponseDTO<ItemDTO> itemDTOPageResponseDTO =
                itemService.list(pageRequestDTO, email);

        itemDTOPageResponseDTO
                .getDtoList()
                .forEach(itemDTO -> log.info(itemDTO));

    }
}