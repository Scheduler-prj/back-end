package PlanQ.PlanQ.question;

import PlanQ.PlanQ.option.OptionService;
import PlanQ.PlanQ.question.dto.request.RequestQuestionDto;
import PlanQ.PlanQ.question.dto.response.ResponseQuestionDto;
import PlanQ.PlanQ.quiz.Quiz;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final OptionService optionService;

    public Question findById(Long id){
        return questionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("문제를 찾을 수 없습니다."));
    }

    public List<ResponseQuestionDto> findByQuestionOfQuiz(Quiz quiz){
        return questionRepository.findAllByQuiz(quiz).stream()
                .map(question -> question.toResponseQuestionDto(optionService.findAllOptions(question)))
                .toList();
    }

    @Transactional
    public Integer solveQuestion(List<RequestQuestionDto> requestQuestionDtoList){
        Integer correctCnt = 0;
        for(RequestQuestionDto requestQuestionDto : requestQuestionDtoList){
            Question question = findById(requestQuestionDto.getId());
            if(question.checkCorrect(requestQuestionDto.getSelectOption())){
                correctCnt++;
            }
        }
        return correctCnt;
    }
}
