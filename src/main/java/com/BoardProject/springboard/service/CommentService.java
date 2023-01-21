package com.BoardProject.springboard.service;

import com.BoardProject.springboard.domain.Board;
import com.BoardProject.springboard.domain.Comment;
import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.dto.CommentDto;
import com.BoardProject.springboard.repository.BoardRepository;
import com.BoardProject.springboard.repository.CommentRepository;
import com.BoardProject.springboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
