package com.BoardProject.springboard.repository;

import com.BoardProject.springboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
//@RequiredArgsConstructor
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    List<Member> findAll();
    Optional<Member> findById(Long id);

    @Override
    <S extends Member> S save(S entity);
}
