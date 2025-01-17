package PlanQ.PlanQ.routine.dto.request;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.embeddad.Calender;
import PlanQ.PlanQ.routine.Dotw;
import PlanQ.PlanQ.routine.Routine;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoutineDto {

    @Valid
    @NotNull
    private Calender calender;

    @NotEmpty
    private List<@NotBlank String> dotws;

    public Routine toEntity(Member member){
        return Routine.builder()
                .requestRoutineDto(this)
                .dotws(changeEnum(this.dotws))
                .member(member)
                .build();
    }
    public List<Dotw> changeEnum(List<String> dotws){
        Set<Dotw> dotwSet = new HashSet<>();
        for(String s_dotw : dotws){
            Dotw dotw = Dotw.valueOf(s_dotw);
            dotwSet.add(dotw);
        }
        return new ArrayList<>(dotwSet);
    }
}
