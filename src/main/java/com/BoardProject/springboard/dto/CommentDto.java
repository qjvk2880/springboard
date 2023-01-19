package com.BoardProject.springboard.dto;

import com.BoardProject.springboard.domain.Board;
import com.BoardProject.springboard.domain.Comment;
import com.BoardProject.springboard.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private String content;
    private LocalDateTime created_date;
    private String createdBy;

//    private Character delete_check;
    private Board board;
    private Member member;

    @Builder
    public CommentDto(String content, LocalDateTime created_date, String createdBy, Board board, Member member) {
        this.content = content;
        this.created_date = created_date;
        this.createdBy = createdBy;
        this.board = board;
        this.member = member;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .created_date(created_date)
                .createdBy(createdBy)
                .member(member)
                .board(board)
                .build();

    }
}