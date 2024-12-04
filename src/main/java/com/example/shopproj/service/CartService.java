package com.example.shopproj.service;

import com.example.shopproj.dto.CartDetailDTO;
import com.example.shopproj.dto.CartItemDTO;
import com.example.shopproj.entity.Cart;
import com.example.shopproj.entity.CartItem;
import com.example.shopproj.entity.Item;
import com.example.shopproj.entity.Member;
import com.example.shopproj.repository.CartItemRepository;
import com.example.shopproj.repository.CartRepository;
import com.example.shopproj.repository.ItemRepository;
import com.example.shopproj.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    // 아이템을 찾아야 해서 findById(item.id)
    private final MemberRepository memberRepository;
    // 회원 찾아야 해서 findByEmail (누구의 장바구니인지 찾아야해서)
    private final CartRepository cartRepository;
    // 장바구니에 넣을 장바구니 아이템을 만들려면 아이템을 참조해서
    // 그걸 가지고 장바구니 아이템을 만들어야 해서 그리고 등등등
    private final CartItemRepository cartItemRepository;

    // 등록 장바구니 만들기
    // 장바구니를 따로 만들지는 않고 장바구니에 넣을 아이템들이 컨트롤러로 들어오면
    // 그 값을 가지고 넣을 것이고 컨트롤ㄹ러에서 들어오는 email을 통해서 멤버를 찾을 예정
    public Long addCart(CartItemDTO cartItemDTO, String email){
        log.info("장바구니 서비스로 들어온 email" +email);
        log.info("장바구니 서비스로 들어온 cartItemDTO" +cartItemDTO);

        Member member =
                memberRepository.findByEmail(email);
        log.info("장바구니 서비스에서 찾은 email" +email);


        // 너가 산다고 한 장바구니에 넣는다고 한 장바구니 아이템이
        // 없는 아이템이라면? 예외처리
        Item item =
        itemRepository.findById(cartItemDTO.getItemid()).orElseThrow(EntityNotFoundException::new);
        log.info("장바구니 서비스에서 찾은 item" +item);


        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }
        // 장바구니가 없으면 만들고 있으면 있는거에
        // 장바구니 아이템들을 만들어서 넣어주고 저장한다.
        // 이미 장바구니에 동일 상품이 이미 등록되어 있다면 해당 등록된 아이템의 수량 증가
        CartItem savedCartItem =
                cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if (savedCartItem != null){
            // 수량증가
            savedCartItem.addCount(cartItemDTO.getCount());
            // 저장된 장바구니에서 장바구니아이템의 pk를 반환
            return savedCartItem.getId();
        }else {
            CartItem cartItem =
                    CartItem.createCartItem(cart, item, cartItemDTO.getCount());

            // 장바구니에 장바구니 아이템 저장
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    public List<CartDetailDTO> getCartList(String email){
        //장바구니의 pk는 1:1 관계이기 때문에 그리고 email은 member 테이블에서
        // 유니크키 이기에 유일하다 멤버는 1, 그에 관계의 장바구니도 1

        List<CartDetailDTO> cartDetailDTOList = new ArrayList<>();

        Member member =
                memberRepository.findByEmail(email);

        Cart cart =
                cartRepository.findByMemberId(member.getId());
//        Cart cart = cartRepository.findByMemberEmail(email);

        if (cart == null){
            return cartDetailDTOList;
        }
        // 장바구니에 담겨있는 상품 정보를 조회
        cartDetailDTOList = cartItemRepository.findCartDetailDTOList(cart.getId());

        return cartDetailDTOList;

    }

}
