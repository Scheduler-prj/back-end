package PlanQ.PlanQ.routine.dto.response;

import PlanQ.PlanQ.routine.Dotw;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRoutineDto {

    private Long routineId;

    private String title;

    private List<String> dotws;

    private boolean alarm;

    private String comment;

    private boolean isClear;
}
