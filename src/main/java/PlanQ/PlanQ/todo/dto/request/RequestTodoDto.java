package PlanQ.PlanQ.todo.dto.request;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.todo.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTodoDto {

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime todoAt;

    private String color;

    @NotNull
    private boolean planAlarm;

    private String planComment;

    public Todo toEntity(Member member, Color color){
        return Todo.builder()
                .requestTodoDto(this)
                .member(member)
                .color(color)
                .build();
    }

}
