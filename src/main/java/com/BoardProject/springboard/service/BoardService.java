package com.BoardProject.springboard.service;

import com.BoardProject.springboard.domain.Board;
import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.dto.BoardDto;
import com.BoardProject.springboard.repository.BoardRepository;
import com.BoardProject.springboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Long saveBoard(BoardDto boardDto,Member member) {
        boardRepository.save(boardDto.toEntity(member));
        return boardDto.getId();
    }

    public List<Board> getAllBoardList() {
        return boardRepository.findAll();
    }
}
