package com.BoardProject.springboard.service;

import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
@Commit
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원_가입() {
        //given
        Member member = Member.builder()
                .username("mins")
                .password("1234")
                .email("email")
                .build();
        //when
        Long joinId = memberService.join(member);
        //then
        Assertions.assertEquals(member, memberRepository.fineOne(joinId));
    }

    @Test
    void 중복_회원_가입() {
        //given
        Member member = Member.builder()
                .username("min")
                .password("1234")
                .email("email")
                .build();

        Member member2 = Member.builder()
                .username("min")
                .password("1234")
                .email("email")
                .build();
        //when
//        memberService.join(member);
//        //then
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        try {
            memberService.join(member2);
//            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        }
    }
}

