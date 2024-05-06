package com.kerem.quizspringbootproject.Mapper;

import com.kerem.quizspringbootproject.Dto.Response.GetAnswerDto;
import com.kerem.quizspringbootproject.Entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    @Mapping(target = "questionId", source = "question.id")
    @Mapping(target = "isCorrect", source = "correct")
    GetAnswerDto answerToGetAnswerDto(Answer answer);
}
