package com.BoardProject.springboard.controller;

import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

//    @GetMapping("/memberLoginForm")
//    public String login(){
//        return "member/memberLoginForm";
//    }

    @GetMapping("/memberJoinForm")
    public String addForm(){
        return "member/memberJoinForm";
    }

    @PostMapping("/memberJoinForm")
    public String createMember(@ModelAttribute Member member) {
        memberService.join(member);
        return "member/memberSaved";
    }
}
