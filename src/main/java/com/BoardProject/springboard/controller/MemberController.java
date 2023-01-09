package com.BoardProject.springboard.controller;

import com.BoardProject.springboard.domain.Board;
import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.dto.MemberDto;
import com.BoardProject.springboard.service.BoardService;
import com.BoardProject.springboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping("/memberJoinForm")
    public String addForm() {
        return "member/memberJoinForm";
    }

    @PostMapping("/memberJoinForm")
    public String createMember(@ModelAttribute MemberDto member) {
        memberService.joinUser(member);

        return "redirect:/";
    }

    @GetMapping("/memberLoginForm")
    public String login() {
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
        model.addAttribute("members", members);
        return "member/memberList";
    }

    @GetMapping("/memberInfo/{username}")
    public String userInfo(@PathVariable String username, Model model) {
        Member member = memberService.findByUsername(username).get();
        List<Board> boards = boardService.getUserBoardList(username);
        model.addAttribute("member", member);
        model.addAttribute("boards", boards);
        return "member/memberInfo";
    }
}
