package com.example.shopproj.repository;

import com.example.shopproj.constant.ItemSellStatus;
import com.example.shopproj.entity.Item;
import com.example.shopproj.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager entityManager;

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

    @Test
    public void priceSearchTest(){
        //가격 검색 테스트
        //사용자가 검색창에 혹은
        // 검색이 가능하도록 만들어놓은 곳에 값을 입력한
        // 이조간에 부합하는 아이템 리스트 검색





    }

    @Test
    @DisplayName("페이징 추가까지")

    public void findByPriceGreaterThanEqualTest(){
        Pageable pageable = PageRequest
                .of(0, 5,  Sort.by("id").ascending());

            Integer price = 10020;
            List<Item> itemList =
                    itemRepository.findByPriceGreaterThanEqual(price,pageable);

            itemList.forEach(item -> log.info(item));

    }
    @Test
    public void nativeQueryTest(){

        Pageable pageable = PageRequest.of(0,5,Sort.by("price").descending());
        String a = "테스트상품1";

        List<Item> itemList =
        itemRepository.nativeQuerySelectWhereNamelike(a, pageable);
        itemList.forEach(item -> log.info(item));

    }
    @Test
    public void queryDslTest(){

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;
        QItem qItem = QItem.item;
        // select = from item

        JPAQuery<Item> query =
                jpaQueryFactory.selectFrom(qItem)
                        .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                        .where(qItem.itemDetail.like("%" + "1" + "%"))
                                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch();

        for (Item item : itemList){
            System.out.println(item.getItemNm());
        }



    }

    @Test
    public void queryDslTestB(){

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QItem qItem = QItem.item;
        // select = from item
        // 상품 검색 조건 입력 값
        String keyword = "1";
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (keyword != null){
            booleanBuilder.or(qItem.itemDetail.like("%"+keyword+"%"));

        }
        if (itemSellStatus != null){
            if (itemSellStatus == ItemSellStatus.SELL){
                booleanBuilder.or(qItem.itemSellStatus.eq(ItemSellStatus.SELL));
            }else {
                booleanBuilder.or(qItem.itemSellStatus.eq(ItemSellStatus.SOLD_OUT));
            }

        }



        JPAQuery<Item> query =
                jpaQueryFactory.selectFrom(qItem)
                        .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                        .where(qItem.itemDetail.like("%" + "1" + "%"))
                        .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch();

        for (Item item : itemList){
            System.out.println(item.getItemNm());
        }



    }

}