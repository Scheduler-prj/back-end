package PlanQ.PlanQ.DashBoard.DTO;

import PlanQ.PlanQ.global.Color;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
//@AllArgsConstructor
@Builder
public class DailyQuizDto {

    private String category;
    private String title;
    private int questionCnt;
    private boolean isSolved;
    private Color color;

    // IntelliJ에서 JPQL과 DTO 매핑 시 이를 인식하지 못하는 경우가 있어 명시적으로 작성했습니다.
    public DailyQuizDto(String category, String title, int questionCnt, boolean isSolved, Color color) {
        this.category = category;
        this.title = title;
        this.questionCnt = questionCnt;
        this.isSolved = isSolved;
        this.color = color;
    }
}