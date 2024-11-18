package com.example.shopproj.repository;

import com.example.shopproj.constant.ItemSellStatus;
import com.example.shopproj.entity.Item;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {


        for (int i = 0; i < 200; i++) {

            Item item =
                    Item.builder()
                            .itemNm("테스트상품")
                            .price(10000)
                            .itemDetail("테스트상품 상세설명")
                            .itemSellStatus(ItemSellStatus.SELL)
                            .stockNumber(100)
                            .regTime(LocalDateTime.now())
                            .updateTime(LocalDateTime.now())
                            .build();

            item.setItemNm(item.getItemNm() + i);
            item.setItemDetail(item.getItemDetail() + i);
            item.setPrice(item.getPrice() + i);

            Item item1 =
                    itemRepository.save(item);

            log.info(item1);
        }

    }

    @Test
    @DisplayName("제품명으로 검색 2가지에서 다시 2가지 검색")
    public void findByItemNmTest() {
        List<Item> itemList =
                itemRepository.findByItemNm("테스트상품1");

        itemList.forEach(item -> log.info(item));
        System.out.println("---------------------------");

        itemList =
                itemRepository.selectWithNmLike("테스트상품2");
        itemList.forEach(item -> log.info(item));
        System.out.println("---------------------------");

        itemList =
                itemRepository.findByItemNm("테스트");
        itemList.forEach(item -> log.info(item));
        System.out.println("---------------------------");

        itemList =
                itemRepository.findByItemNmContaining("4");
        itemList.forEach(item -> log.info(item));
        System.out.println("---------------------------");


    }

}