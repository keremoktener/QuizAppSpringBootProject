package com.kerem.quizspringbootproject.Controller;

import static com.kerem.quizspringbootproject.Constants.EndPoints.*;

import com.kerem.quizspringbootproject.Constants.EndPoints;
import com.kerem.quizspringbootproject.Dto.Request.CreateQuestionDto;
import com.kerem.quizspringbootproject.Dto.Response.GetQuestionDto;
import com.kerem.quizspringbootproject.Dto.Response.QuestionAnswerDto;
import com.kerem.quizspringbootproject.Entity.Answer;
import com.kerem.quizspringbootproject.Entity.Question;
import com.kerem.quizspringbootproject.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ROOT + QUESTION)
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping(SAVE)
    public String createQuestionWithAnswers(@RequestBody CreateQuestionDto dto) {
        questionService.saveQuestionDto(dto);
        return "Soru başarıyla kaydedildi!";
    }

    @PutMapping(UPDATE)
    public String updateQuestionWithAnswers(Long questionId, String questionText) {
        Question question = questionService.updateQuestionById(questionId, questionText);
        if (question == null) {
            return "Soru bulunamadı!";
        } else {
            return "Soru başarıyla güncellendi!";
        }
    }

    @DeleteMapping(DELETE)
    public String deleteQuestionById(Long questionId) {
        questionService.deleteQuestionById(questionId);
        return "Soru başarıyla silindi!";
    }

    @GetMapping(FINDALL)
    public List<GetQuestionDto> findAllQuestions() {
        List<GetQuestionDto> allQuestionsDto = questionService.findAllQuestionsDto();
        if (allQuestionsDto == null) {
            throw new RuntimeException("Soru bulunmamaktadır!");
        } else {
            return allQuestionsDto;
        }
    }

    @GetMapping(FINDBYID)
    public GetQuestionDto findQuestionById(Long questionId) { // sout ile hata vcer
        GetQuestionDto questionByIdDto = questionService.findQuestionByIdDto(questionId);
        if (questionByIdDto == null) {
            throw new RuntimeException(questionId + " id'li soru bulunamadı!");
        } else {
            return questionByIdDto;
        }
    }

    @GetMapping(GETQUESTIONSWITHANSWERS)
    public List<QuestionAnswerDto> printAllQuestionsWithTheirAnswers() {
        return questionService.printAllQuestionsWithTheirAnswers();
    }
}
