package com.example.blog.repository.qna;

import com.example.blog.model.qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna,Long>, QnaCustomRepository {
}
