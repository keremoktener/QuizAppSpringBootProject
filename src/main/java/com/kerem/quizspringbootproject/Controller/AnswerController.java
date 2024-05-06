package com.kerem.quizspringbootproject.Controller;

import com.kerem.quizspringbootproject.Dto.Request.CreateAnswerDto;
import com.kerem.quizspringbootproject.Dto.Response.GetAnswerDto;
import com.kerem.quizspringbootproject.Entity.Answer;
import com.kerem.quizspringbootproject.Service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kerem.quizspringbootproject.Constants.EndPoints.*;

@RestController
@RequestMapping(ROOT + ANSWER)
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @DeleteMapping(DELETEBYQUESTIONID)
    public void deleteAnswersByQuestionId(Long questionId) {
        answerService.deleteAnswersByQuestionId(questionId);
    }

    @PostMapping(SAVE)
    public String saveAnswerDto(Long questionId, @RequestBody List<CreateAnswerDto> answerDtos) {
        List<Answer> answerList = answerService.saveAnswerDtoByQuestionId(questionId, answerDtos);
        if (answerList == null) {
            return "Soru bulunamadı!";
        } else {
            return "Cevaplar başarıyla kaydedildi!";
        }
    }

    @PutMapping(UPDATE)
    public String  updateAnswerDto(Long questionId, @RequestBody List<CreateAnswerDto> answerDtos) {
        answerService.updateAnswerDtoByQuestionId(questionId, answerDtos);
        return "Cevaplar başarıyla güncellendi!";
    }

    @GetMapping(FINDALL)
    public List<GetAnswerDto>  findAllAnswers() {
        List<GetAnswerDto> allAnswers = answerService.findAllAnswersDto();
        if (allAnswers == null) {
            throw new RuntimeException("Cevaplar bulunamadı!");
        } else {
            return allAnswers;
        }
    }

    @GetMapping(FINDBYQUESTIONID)
    public List<GetAnswerDto> findAnswersByQuestionId(Long questionId) {
        List<GetAnswerDto> answersByQuestionId = answerService.findAnswersByQuestionId(questionId);
        if (answersByQuestionId.isEmpty()) {
            throw new RuntimeException(questionId + " id'li soruya ait cevaplar bulunamadı!");
        } else {
            return answersByQuestionId;
        }
    }
}
