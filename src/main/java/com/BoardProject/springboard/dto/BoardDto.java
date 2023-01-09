package com.BoardProject.springboard.dto;

import com.BoardProject.springboard.domain.Board;
import com.BoardProject.springboard.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    private Long countVisit;

    @Builder
    public BoardDto(String title, String content, String createdBy,Long countVisit) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.countVisit = countVisit;
    }
    public Board toEntity(Member member){
        return Board.builder()
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .countVisit(countVisit)
                .member(member)
                .build();
    }

    public BoardDto(Board board) {
        id = board.getId();
        title = board.getTitle();
        content = board.getContent();
        createdBy = board.getCreatedBy();
        countVisit = board.getCountVisit();
    }


}
