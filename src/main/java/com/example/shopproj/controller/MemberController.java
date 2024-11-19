package com.example.shopproj.controller;

import com.example.shopproj.dto.MemberDTO;
import com.example.shopproj.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberDTO",new MemberDTO());

        return "member/memberForm";
    }

    @PostMapping("/new")
    public String memberFormPost(@Valid MemberDTO memberDTO, BindingResult bindingResult,
                                 Model model){
        //입력을 받을 때 유효성검사를 할것이며, // valid
        log.info("저장의 post로 들어온 memberDTO" + memberDTO);
        if (bindingResult.hasErrors()){
            // 유효성검사에 이상이 있다면
            // 다시 회원가입페이지로 보낼것이다.
            // 에러내용을 가지고 보낸다. 자동으로 넘어간다.
            // 단 return 으로 redirect 안됨 그건 RedirectAttributes에 따로 담아야함
            // 어떤 에러인지 로그
            log.info(bindingResult.getAllErrors());
            // 이 에러를 가져오는 getAllErrors의 내용을 리다이렉트로 보낼때 가져가면 된다.

            return "member/memberForm";
        }
        // 유효성검사에 이상이 있다면 다시 회원가입페이지로 보낼것이다.

        try {
            memberService.saveMember(memberDTO);

        }catch (IllegalStateException e){
            model.addAttribute("msg", e.getMessage());
            return "member/memberForm";
        }

        // 저장후 특정페이지로 이동하게 만들것이다.

        return null;
    }

    @GetMapping("/login")
    public String login(){

        return "member/loginForm";
    }






}
