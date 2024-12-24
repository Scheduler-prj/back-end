package PlanQ.PlanQ.DashBoard.DTO;

import PlanQ.PlanQ.global.Color;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@Builder
public class IncorrectQuestionDto {

    private Color color;
    private String quizTitle;
    private int num;
    private String questionTitle;
}
