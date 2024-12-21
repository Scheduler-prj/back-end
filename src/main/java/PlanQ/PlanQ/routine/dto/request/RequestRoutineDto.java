package PlanQ.PlanQ.routine.dto.request;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.routine.Routine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoutineDto {
    @NotBlank
    private String title;

    @NotBlank
    private boolean alarm;

    @NotBlank
    private String comment;

    @NotBlank
    private List<String> dotw;

    public Routine toEntity(Member member){
        return Routine.builder()
                .requestRoutineDto(this)
                .member(member)
                .build();
    }
}
