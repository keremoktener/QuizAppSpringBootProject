package com.kerem.quizspringbootproject.Mapper;

import com.kerem.quizspringbootproject.Dto.Request.CreateQuestionDto;
import com.kerem.quizspringbootproject.Dto.Response.GetQuestionDto;
import com.kerem.quizspringbootproject.Entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    Question createQuestionDtoToQuestion(CreateQuestionDto dto);

    GetQuestionDto questionToGetQuestionDto(Question question);
}
