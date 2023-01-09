package com.BoardProject.springboard.controller;

import com.BoardProject.springboard.domain.Board;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/boardForm")
    public String addBoard() {
        return "/board/boardForm";
    }

    @PostMapping("/boardForm")
    public String createBoard(@ModelAttribute BoardDto boardDto) {

        String username = memberService.getAuthUsername();

        Member member = memberService.findByUsername(username).get();
        boardDto.setCreatedBy(username);
        boardDto.setCountVisit(1L);
        boardService.saveBoard(boardDto, member);

        return "redirect:/board/boardList";
    }

    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<Board> boards = boardService.getAllBoardList();
        model.addAttribute("boards",boards);
        return "/board/boardList";
    }

    @GetMapping("/boardContent/{id}")
    public String boardContent(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id).get();
        boardService.updateVisit(board.getId());

        model.addAttribute(board);
        return "/board/boardContent";

    }

    @GetMapping("/delete")
    public String boardDelete(Long id){
        boardService.delete(id);
        return "redirect:/board/boardList";
    }
}
