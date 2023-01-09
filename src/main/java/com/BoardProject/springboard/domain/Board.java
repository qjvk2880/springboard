package com.BoardProject.springboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public void setMember(Member member){
        this.member = member;
        member.getBoardList().add(this);
    }

    @Builder
    public Board(String title, String content, String createdBy, Long countVisit,Member member) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.countVisit = countVisit;
        if (this.member != null) {
            member.getBoardList().remove(this);
        }
        this.member = member;
    }

    public void updateVisit(Long countVisit) {
        this.countVisit = countVisit;
    }
}
