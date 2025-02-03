package PlanQ.PlanQ.question.dto.response;

import PlanQ.PlanQ.option.dto.response.ResponseOptionDto;
import PlanQ.PlanQ.question.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseQuestionDto {
    private Long id;

    private Integer questionNum;

    private String content;

    private Integer correct;

    private Integer selectOption;

    private boolean isCorrect;

    private String questionType;

    private List<ResponseOptionDto> options;
}
