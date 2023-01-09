package com.BoardProject.springboard.controller;

import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.dto.BoardDto;
import com.BoardProject.springboard.repository.BoardRepository;
import com.BoardProject.springboard.service.BoardService;
import com.BoardProject.springboard.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
//    private final BoardRepository boardRepository;
    @GetMapping("/boardForm")
    public String addBoard() {
        return "/board/boardForm";
    }

    @PostMapping("/boardForm")
    public String createBoard(@ModelAttribute BoardDto boardDto, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();

        Optional<Member> member = memberService.findByUsername(username);
        boardDto.setCreatedBy(username);
        boardDto.setCountVisit(1L);
        boardService.saveBoard(boardDto,member.get());

        return "redirect:/";
    }
}
