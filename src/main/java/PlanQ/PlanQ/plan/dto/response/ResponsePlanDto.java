package PlanQ.PlanQ.plan.dto.response;

import PlanQ.PlanQ.global.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePlanDto {
    private Long planId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String color;
    private boolean alarm;
    private String comment;
}
