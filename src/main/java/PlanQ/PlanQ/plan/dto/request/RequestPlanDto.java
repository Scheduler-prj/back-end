package PlanQ.PlanQ.plan.dto.request;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.embeddad.Calender;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.plan.Plan;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPlanDto {
    @NotNull
    private LocalDateTime startDate;
    
    @NotNull
    private LocalDateTime endDate;

    @Valid
    @NotNull
    private Calender calender;

    public Plan toEntity(Member member){
        return Plan.builder()
                .member(member)
                .requestPlanDto(this)
                .build();
    }
}
