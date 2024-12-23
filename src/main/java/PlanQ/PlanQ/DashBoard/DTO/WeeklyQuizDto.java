package PlanQ.PlanQ.DashBoard.DTO;

import lombok.*;

@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@Builder
public class WeeklyQuizDto {

    private int totalNum;
    private int correctNum;
    private int incorrectNum;

}
