package com.kerem.quizspringbootproject.Dto.Response;

public record GetAnswerDto(Long questionId, String answerText, Boolean isCorrect) {
}
