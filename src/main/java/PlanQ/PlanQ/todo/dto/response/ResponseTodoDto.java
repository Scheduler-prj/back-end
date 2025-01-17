package PlanQ.PlanQ.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTodoDto {
    private Long todoId;
    private String title;
    private String planComment;
    private LocalDateTime todoAt;
    private String color;
    private boolean planAlarm;
    private boolean isClear;

}
