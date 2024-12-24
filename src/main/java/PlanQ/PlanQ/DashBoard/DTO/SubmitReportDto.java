package PlanQ.PlanQ.DashBoard.DTO;

import PlanQ.PlanQ.global.Color;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@Builder
public class SubmitReportDto {

    private String reportTitle;
    private LocalDateTime date;
    private String comment;
    private Color color;

}
