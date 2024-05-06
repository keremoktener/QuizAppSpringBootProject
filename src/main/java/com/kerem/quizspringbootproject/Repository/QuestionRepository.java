package com.kerem.quizspringbootproject.Repository;

import com.kerem.quizspringbootproject.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
