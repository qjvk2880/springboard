package com.BoardProject.springboard.domain;

import com.BoardProject.springboard.dto.BoardDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Getter @Setter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    private Long countVisit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    private List<Comment> boardCommentList = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
        member.getBoardList().add(this);
    }

    @Builder
    public Board(String title, String content, String createdBy, Long countVisit,Member member,List<Comment> boardCommentList) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.countVisit = countVisit;
        if (this.member != null) {
            member.getBoardList().remove(this);
        }
        this.member = member;
//        this.boardCommentList = boardCommentList;
    }

    public void updateVisit(Long countVisit) {
        this.countVisit = countVisit;
    }

    public void updateBoard(BoardDto boardDto) {
        this.content = boardDto.getContent();
        this.title = boardDto.getTitle();
    }
}
