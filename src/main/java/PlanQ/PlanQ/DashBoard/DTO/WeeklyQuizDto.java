package PlanQ.PlanQ.DashBoard.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
//@AllArgsConstructor
@Builder
public class WeeklyQuizDto {

    private int questionCnt;
    private int correctCnt;
    private int incorrectNum;
    private LocalDateTime solvedTime;
    private String dotw;

    // IntelliJ에서 JPQL과 DTO 매핑 시 이를 인식하지 못하는 경우가 있어 명시적으로 작성했습니다.
    public WeeklyQuizDto(int questionCnt, int correctCnt, int incorrectNum, LocalDateTime solvedTime, String dotw) {
        this.questionCnt = questionCnt;
        this.correctCnt = correctCnt;
        this.incorrectNum = incorrectNum;
        this.solvedTime = solvedTime;
        this.dotw = dotw;
    }
}
