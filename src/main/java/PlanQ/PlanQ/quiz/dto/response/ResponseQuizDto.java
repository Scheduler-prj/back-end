package PlanQ.PlanQ.quiz.dto.response;

import PlanQ.PlanQ.question.dto.response.ResponseQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseQuizDto {
    private Long id;

    private String title;

    private LocalDateTime reviewDate;

    private LocalTime solveTime;

    private String category;

    private Integer correctCnt;

    private Integer questionCnt;

    private boolean isSolved;

    private boolean favorite;

    private String color;

    private List<ResponseQuestionDto> questionList;
}
