package com.BoardProject.springboard.controller;

import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.dto.MemberDto;
import com.BoardProject.springboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/memberJoinForm")
    public String addForm(){
        return "member/memberJoinForm";
    }

    @PostMapping("/memberJoinForm")
    public String createMember(@ModelAttribute MemberDto member) {
        System.out.println("member.getPassword() = " + member.getPassword());

        memberService.joinUser(member);

        return "redirect:/";
    }

    @GetMapping("/memberLoginForm")
    public String login(){
//        return "member/memberLoginForm";
        return "member/memberLoginForm";
    }

    @GetMapping("/memberLoginResult")
    public String loginResult() {
        return "member/memberLoginResult";
    }

    @GetMapping("/memberList")
    public String findAllMember(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);
        return "member/memberList";
    }
}
