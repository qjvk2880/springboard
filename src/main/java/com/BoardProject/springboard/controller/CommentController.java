package com.BoardProject.springboard.controller;

import com.BoardProject.springboard.domain.Board;
import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.dto.CommentDto;
import com.BoardProject.springboard.service.BoardService;
import com.BoardProject.springboard.service.CommentService;
import com.BoardProject.springboard.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;
    @PostMapping("/write/{board_id}")
    public String postComment(@PathVariable Long board_id, @ModelAttribute CommentDto commentDto) {
        String username = memberService.getAuthUsername();
        Member member = memberService.findByUsername(username).get();
        Board board = boardService.findById(board_id).get();

        LocalDateTime now = LocalDateTime.now();

        commentDto.setCreatedBy(username);
        commentDto.setCreated_date(now);
        commentDto.setMember(member);
        commentDto.setBoard(board);
        commentService.saveCommnet(commentDto);
        return "redirect:/board/boardList";
    }

}
