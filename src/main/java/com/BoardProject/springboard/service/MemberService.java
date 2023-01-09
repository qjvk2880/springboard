package com.BoardProject.springboard.service;

import com.BoardProject.springboard.domain.Member;
import com.BoardProject.springboard.domain.Role;
import com.BoardProject.springboard.dto.MemberDto;
import com.BoardProject.springboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        return memberRepository.save(memberDto.toEntity(encodedPassword)).getId();

    }

    public Optional<Member> findByUsername(String username){
        return memberRepository.findByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> findName = memberRepository.findByUsername(username);
        Member member = findName.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (("admin").equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }
        return new User(member.getUsername(), member.getPassword(), authorities);
    }

}
