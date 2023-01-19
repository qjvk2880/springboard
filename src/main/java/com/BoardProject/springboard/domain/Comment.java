package com.BoardProject.springboard.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String content;
    private LocalDateTime created_date;
    private String createdBy;

//    private Character delete_check;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(String content, LocalDateTime created_date, String createdBy, Board board, Member member) {
        this.content = content;
        this.created_date = created_date;
        this.createdBy = createdBy;
//        if(this.board != null){
//            board.getBoardCommentList().remove(this);
//        }else
//            this.board = board;
//        if(this.member != null){
//            member.getBoardCommentList().remove(this);
//        }else
        this.board = board;
        this.member = member;
    }
}
