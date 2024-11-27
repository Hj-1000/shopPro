package com.example.shopproj.controller;

import com.example.shopproj.dto.ItemDTO;
import com.example.shopproj.dto.PageRequestDTO;
import com.example.shopproj.dto.PageResponseDTO;
import com.example.shopproj.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Pageable;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MainController {

    private final ItemService itemService;

    // 작성자에 의해서 // 판매중 // 일자 순으로 검색하는 ItemSearchDTO
    // page 현재페이지 받고 // size도 받고 // pageable로 페이징처리 위해서 받고

    @GetMapping(value = "/")
    public String main(PageRequestDTO pageRequestDTO, Model model){

        // 기존에 만들었던 list는 pageable과 email을 받음으로
        // 자신이 만든 item 들의 list 만 받아왔다.
        // 그러면 email이 null 그러니까 로그인을 안한 사람은 못본다.
        // 그래서 list를 추가로 만들던가 아니면 null일 경우와
        // 아닐 경우에 따라서 동적인 쿼리를 만들면 된다.
        //  그러면 자신이 만든 아이템만 봐야하므로 메인창의 리스트를 보이는 메서드를 새로 만들자

        pageRequestDTO.setSize(6);
        PageResponseDTO<ItemDTO> itemDTOPageResponseDTO =
        itemService.mainList(pageRequestDTO);
        // 현재 페이지, 1페이지당 사이즈, 검색키워드 등등이 있다면 그에 맞는 값들
        // startm, end, pre, next 이런것들 총 페이지수 등등
        model.addAttribute("itemDTOPageResponseDTO", itemDTOPageResponseDTO);


        return "main";
    }


}
