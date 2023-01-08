package com.BoardProject.springboard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter @Setter
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private Long createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
}
