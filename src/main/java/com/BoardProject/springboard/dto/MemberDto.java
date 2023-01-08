package com.BoardProject.springboard.dto;

import com.BoardProject.springboard.domain.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String email;

    public Member toEntity(String encodedPassword) {

        return Member.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .build();
    }
}
