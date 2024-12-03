package com.example.shopproj.repository;

import com.example.shopproj.dto.CartDetailDTO;
import com.example.shopproj.entity.CartItem;
import com.example.shopproj.entity.ItemImg;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;




@SpringBootTest
@Log4j2
class CartItemRepositoryTest {
    @Autowired
    CartItemRepository cartItemRepository;

//    @Test
//    public void findCartDetailDTOListTest(){
//
//        List<CartDetailDTO> cartDetailDTOList =
//                cartItemRepository.findCartDetailDTOList(1L);
//
//        cartDetailDTOList.forEach(a->log.info(a));
//
//        List<CartItem> cartItemList =
//                cartItemRepository.findByCartId(1L);
//
//        cartItemList.forEach(cartDetailDTO -> log.info(cartDetailDTO));
//
//        for (CartItem cartItem : cartItemList){
//            CartDetailDTO detailDTO = new CartDetailDTO();
//            detailDTO.setCartItemId(detailDTO.getCartItemId());
//            detailDTO.setItemNm(detailDTO.getItemNm());
//            detailDTO.setCount(detailDTO.getCount());
//            detailDTO.setPrice(detailDTO.getPrice());
//            List<ItemImg> itemImgList =
//            cartItem.getItem().getItemImgList();
//                    for(ItemImg itemImg : itemImgList){
//                        if (itemImg.getRepimgYn().equals("Y")){
//                            detailDTO.setImgUrl(itemImg.getImgUrl());
//                        }
//                    }
//        }
//    }

}