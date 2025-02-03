package PlanQ.PlanQ.quiz.dto.request;

import PlanQ.PlanQ.question.dto.request.RequestQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSolveDto {
    private List<RequestQuestionDto> requestQuestionDtoList;
}
