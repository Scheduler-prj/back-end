package PlanQ.PlanQ.plan.dto.request;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.embeddad.Calender;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.plan.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPlanDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Calender calender;

    public Plan toEntity(Member member){
        return Plan.builder()
                .member(member)
                .requestPlanDto(this)
                .build();
    }
}
