package com.kerem.quizspringbootproject.Service;

import com.kerem.quizspringbootproject.Dto.Request.CreateQuestionDto;
import com.kerem.quizspringbootproject.Dto.Response.GetQuestionDto;
import com.kerem.quizspringbootproject.Dto.Response.QuestionAnswerDto;
import com.kerem.quizspringbootproject.Entity.Answer;
import com.kerem.quizspringbootproject.Entity.Question;
import com.kerem.quizspringbootproject.Mapper.QuestionMapper;
import com.kerem.quizspringbootproject.Repository.AnswerRepository;
import com.kerem.quizspringbootproject.Repository.QuestionRepository;
import com.kerem.quizspringbootproject.Utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService extends ServiceManager<Question, Long> {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        super(questionRepository);
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public void saveQuestionDto(CreateQuestionDto dto) {
        Question question = QuestionMapper.INSTANCE.createQuestionDtoToQuestion(dto);
        questionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }

    public List<GetQuestionDto> findAllQuestionsDto() {
        List<Question> questions = questionRepository.findAll();
        if(questions.isEmpty()){
            return null;
        } else {
            List<GetQuestionDto> getQuestionDtoList = new ArrayList<>();
            for(Question question : questions){
                GetQuestionDto getQuestionDto = QuestionMapper.INSTANCE.questionToGetQuestionDto(question);
                getQuestionDtoList.add(getQuestionDto);
            }
            return getQuestionDtoList;
        }
    }

    public GetQuestionDto findQuestionByIdDto(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.map(QuestionMapper.INSTANCE::questionToGetQuestionDto).orElse(null);
    }

    public Question updateQuestionById(Long id, String questionText){
        Optional<Question> questionById = questionRepository.findById(id);
        if (questionById.isEmpty()){
            return null;
        } else {
            Question question = questionById.get();
            question.setQuestionText(questionText);
            Question savedQuestion = questionRepository.save(question);
            return savedQuestion;
        }
    }

    public List<QuestionAnswerDto> printAllQuestionsWithTheirAnswers() {
        List<Question> allQuestions = questionRepository.findAll();
        List<QuestionAnswerDto> questionAnswerDtoList = new ArrayList<>();
        for (Question question : allQuestions) {
            QuestionAnswerDto dto = new QuestionAnswerDto();
            dto.setQuestionId(question.getId());
            dto.setQuestionText(question.getQuestionText());
            List<Answer> answersForQuestion = answerRepository.findAnswersByQuestionId(question.getId());
            List<String> answerTexts = answersForQuestion.stream().map(Answer::getAnswerText).collect(Collectors.toList());
            dto.setAnswers(answerTexts);
            questionAnswerDtoList.add(dto);
        }
        return questionAnswerDtoList;
    }


}
