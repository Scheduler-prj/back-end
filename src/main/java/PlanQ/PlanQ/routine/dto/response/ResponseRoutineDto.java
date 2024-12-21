package PlanQ.PlanQ.routine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRoutineDto {

    private Long routineId;

    private String title;

    private String dotws;

    private boolean alarm;

    private String comment;

    private boolean isClear;
}
