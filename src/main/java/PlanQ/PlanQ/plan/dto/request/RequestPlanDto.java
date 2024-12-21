package PlanQ.PlanQ.plan.dto.request;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.plan.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPlanDto {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String color;
    private boolean alarm;
    private String comment;

    public Plan toEntity(Member member){
        return Plan.builder()
                .requestPlanDto(this)
                .member(member)
                .build();
    }
}
