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
//    private final EntityManager em;
//
//    public void saveMember(Member member) {
//        em.persist(member);
//    }
//
//    public Member fineOne(Long id) {
//        return em.find(Member.class, id);
//    }
//
//    public List<Member> findAll() {
//        return em.createQuery("select m from Member m")
//                .getResultList();
//    }
//
//    public List<Member> findByName(String username) {
//        return em.createQuery("select m from Member m where m.username = :username ", Member.class)
//                .setParameter("username", username)
//                .getResultList();
//    }
    Optional<Member> findByUsername(String username);
    List<Member> findAll();
    Optional<Member> findById(Long id);

    @Override
    <S extends Member> S save(S entity);
}
