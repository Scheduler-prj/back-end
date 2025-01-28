package PlanQ.PlanQ.DashBoard.DTO;

import PlanQ.PlanQ.global.Color;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
//@AllArgsConstructor
@Builder
public class IncorrectQuestionDto {

    private Color color;
    private String title;
    private int question_num;
    private String content;

    // IntelliJ에서 JPQL과 DTO 매핑 시 이를 인식하지 못하는 경우가 있어 명시적으로 작성했습니다.
    public IncorrectQuestionDto(Color color, String title, int question_num, String content) {
        this.color = color;
        this.title = title;
        this.question_num = question_num;
        this.content = content;
    }
}
