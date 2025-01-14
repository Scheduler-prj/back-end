package PlanQ.PlanQ.routine.dto.request;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.routine.Dotw;
import PlanQ.PlanQ.routine.Routine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoutineDto {
    @NotBlank
    private String title;

    @NotNull
    private boolean alarm;

    @NotBlank
    private String comment;

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
        List<Dotw> dotwList = new ArrayList<>();
        for(String s_dotw : dotws){
            Dotw dotw = Dotw.valueOf(s_dotw);
            dotwList.add(dotw);
        }
        return dotwList;
    }
}
