package com.kerem.quizspringbootproject.Repository;

import com.kerem.quizspringbootproject.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Answer a WHERE a.question.id = ?1")
    void deleteAnswersByQuestionId(Long questionId);

    @Query("SELECT a FROM Answer a WHERE a.question.id = ?1")
    List<Answer> findAnswersByQuestionId(Long questionId);
}
