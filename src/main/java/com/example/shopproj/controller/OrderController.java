package com.example.shopproj.controller;

import com.example.shopproj.dto.ItemDTO;
import com.example.shopproj.dto.OrderDTO;
import com.example.shopproj.entity.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    // 주문하기 // 주문하기는 상품의 읽기 페이지에서
    // 사용자가 볼 수 있으므로, 따로 get으로 읽기 페이지는 만들지 않는다.
    // 대신 그 페이지에서 보내주는 데이터를 바탕으로 order, orderItem
    // 에 데이터를 입력하는 역할을 한다.
    // 2가지 방법이 있다.
    // 1. 일반적인 컨트롤러로 데이터를 받고 다시 주문내역으로 이동한다.
        // 일반적인 컨트롤러를 쓰더라도 pesponseEntity를 사용하여 rest처럼 데이터만을
        // 받을 수 있다.
    // 2. rest 컨트롤러로 데이터를 받고 다시 주문내역으로 이동한다.
    // @RequestBody 쓰면 application/json; charset = utf-8
    @PostMapping("/order")
    public ResponseEntity order(@Valid OrderDTO orderDTO, BindingResult bindingResult, Principal principal){

        if (bindingResult.hasErrors()){
            StringBuffer sb = new StringBuffer();   //엄연히 말하면 String이다

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();  // 각 필드 에러를 가져올 수 있음
            for (FieldError fieldError  : fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST)
        }

        // 주문은 로그인한 사용자 물론 판매자 또한 다른아이템을 자기아이템을
        // 살 수 있다. 로그인한 사람만 이 주소로 들어올 수 있다. 시큐리티에서 확인
        // 또는 들어올 때 principal!=null 이라면 로그인을 한 사람이다.
//
//        log.info("사용자가 현재 주문하려는 아이템 pk" + id);
//        log.info("사용자가 현재 주문하려는 아이템 수량" + count);

        // 유효성 검사

        // 저장을 해야한다.



        if (true){
            // <T>에 따라 원하는걸 보낼 수 있음
            return new ResponseEntity<String>("주문완료", HttpStatus.OK);
        }else {

        }

    // TODO : 아직미완성임
    return null;
    }
}
