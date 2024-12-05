package com.example.shopproj.service;

import com.example.shopproj.dto.CartItemDTO;
import com.example.shopproj.entity.Cart;
import com.example.shopproj.entity.CartItem;
import com.example.shopproj.entity.Member;
import com.example.shopproj.repository.CartItemRepository;
import com.example.shopproj.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;

    //카트에 담긴 카트아이템의 수량을 변경
    // 카트 id는 필요가 없어요 // 카트아이템 id 자식의 직접적인 pk 값을 알고 있어서

    public Long updateCartItemId(CartItemDTO cartItemDTO, String email) throws Exception {
        // 내 카트가 맞는지 확인
        Member member =
        memberRepository.findByEmail(email);
        // 카트 아이디를 받아서 로그인 한 회원정보 와 비교 후에 작성
        // 카트아이템 아이디만 있음

        // 컨트롤러에서 받은 CartItemDTO에 카트아이템아이디로

        Optional<CartItem> optionalCartitem =
        cartItemRepository.findById(cartItemDTO.getItemid());


        CartItem cartItem =
        optionalCartitem.orElseThrow(EntityNotFoundException::new);

//        if(cartItem.getCart()!=null && member !=null && cartItem.getCart().getMember().getId() != member.getId()){        }

        //todo: 이거 한번 잘쓴거 봐야됨 if문 두개인거같은데
        if(cartItem.getCart()!=null && member !=null && cartItem.getCart().getMember().getId() != member.getId()){
            throw new Exception();
        }


        cartItem.setCount(cartItemDTO.getCount());

        return cartItem.getId();

    }


}
