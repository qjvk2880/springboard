package com.BoardProject.springboard.service;

import com.BoardProject.springboard.domain.Board;
import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.dto.CommentDto;
import com.BoardProject.springboard.repository.BoardRepository;
import com.BoardProject.springboard.repository.CommentRepository;
import com.BoardProject.springboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private final CommentRepository commentRepository;

//    @Transactional
    public Long saveCommnet(CommentDto commentDto) {
        commentRepository.save(commentDto.toEntity());
        return commentDto.getId();
    }
}
