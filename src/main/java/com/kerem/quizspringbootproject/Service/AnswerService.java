package com.kerem.quizspringbootproject.Service;

import com.kerem.quizspringbootproject.Dto.Request.CreateAnswerDto;
import com.kerem.quizspringbootproject.Dto.Response.GetAnswerDto;
import com.kerem.quizspringbootproject.Entity.Answer;
import com.kerem.quizspringbootproject.Entity.Question;
import com.kerem.quizspringbootproject.Mapper.AnswerMapper;
import com.kerem.quizspringbootproject.Repository.AnswerRepository;
import com.kerem.quizspringbootproject.Repository.QuestionRepository;
import com.kerem.quizspringbootproject.Utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService extends ServiceManager<Answer, Long> {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        super(answerRepository);
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public void deleteAnswersByQuestionId(Long questionId) {
        answerRepository.deleteAnswersByQuestionId(questionId);
    }

    public List<Answer> saveAnswerDtoByQuestionId(Long questionId, List<CreateAnswerDto> answerDtos) {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isEmpty()) {
            return null;
        } else {
            List<Answer> answerList = new ArrayList<>();
            for (CreateAnswerDto answerDto : answerDtos) {
                if (answerDto.answerText() == null || answerDto.isCorrect() == null) {
                    throw new RuntimeException("Cevep metni veya doğruluk durumu boş olamaz!");
                }
                Answer answer = new Answer();
                answer.setQuestion(question.get());
                answer.setAnswerText(answerDto.answerText());
                answer.setCorrect(answerDto.isCorrect());
                answerRepository.save(answer);
                answerList.add(answer);
            }
            return answerList;
        }

    }
    public void updateAnswerDtoByQuestionId(Long questionId, List<CreateAnswerDto> answerDtos) {
        answerRepository.deleteAnswersByQuestionId(questionId);
        saveAnswerDtoByQuestionId(questionId, answerDtos);
        System.out.println("Cevaplar başarıyla güncellendi!");
    }

    public List<GetAnswerDto> findAllAnswersDto() {
        List<Answer> allAnswers = findAll();
        if (allAnswers.isEmpty()) {
            return null;
        } else {
            List<GetAnswerDto> allAnswersDto = new ArrayList<>();
            for(Answer answer : allAnswers) {
                GetAnswerDto getAnswerDto = AnswerMapper.INSTANCE.answerToGetAnswerDto(answer);
                allAnswersDto.add(getAnswerDto);
            }
            return allAnswersDto;
        }
    }

    public List<GetAnswerDto> findAnswersByQuestionId(Long questionId) {
        List<Answer> answersByQuestionId = answerRepository.findAnswersByQuestionId(questionId);
        if (answersByQuestionId.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<GetAnswerDto> answersByQuestionIdDto = new ArrayList<>();
            for(Answer answer : answersByQuestionId) {
                GetAnswerDto getAnswerDto = AnswerMapper.INSTANCE.answerToGetAnswerDto(answer);
                answersByQuestionIdDto.add(getAnswerDto);
            }
            return answersByQuestionIdDto;
        }
    }


}
