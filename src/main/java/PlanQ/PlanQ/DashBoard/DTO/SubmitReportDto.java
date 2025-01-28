package PlanQ.PlanQ.DashBoard.DTO;

import PlanQ.PlanQ.global.Color;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
//@AllArgsConstructor
@Builder
public class SubmitReportDto {

    private String reportTitle;
    private LocalDateTime date;
    private String comment;
    private Color color;

    // IntelliJ에서 JPQL과 DTO 매핑 시 이를 인식하지 못하는 경우가 있어 명시적으로 작성했습니다.
    public SubmitReportDto(String reportTitle, LocalDateTime date, String comment, Color color) {
        this.reportTitle = reportTitle;
        this.date = date;
        this.comment = comment;
        this.color = color;
    }

}
