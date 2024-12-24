package PlanQ.PlanQ.DashBoard.DTO;

import PlanQ.PlanQ.global.Color;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@Builder
public class DailyQuizDto {

    private String category;
    private String quizTitle;
    private int questionNum;
    private boolean isSolved;
    private Color color;
}
