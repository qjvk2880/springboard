package com.BoardProject.springboard.repository;

import com.BoardProject.springboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);

    void deleteById(Long id);

    List<Board> findAllByCreatedBy(String username);

}
