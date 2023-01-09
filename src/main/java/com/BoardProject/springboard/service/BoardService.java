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

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long saveBoard(BoardDto boardDto, Member member) {
        boardRepository.save(boardDto.toEntity(member));
        return boardDto.getId();
    }

    public List<Board> getAllBoardList() {
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateVisit(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("해당 게시글이 존재하지 않습니다."));
        Long countVisit = board.getCountVisit() + 1L;

        BoardDto boardDto = BoardDto.builder()
                .countVisit(countVisit)
                .build();

        board.setCountVisit(boardDto.getCountVisit());
    }

    public List<Board> getUserBoardList(String username) {
        return boardRepository.findAllByCreatedBy(username);
    }
}
