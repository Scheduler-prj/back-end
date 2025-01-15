package PlanQ.PlanQ.todo.dto.request;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.embeddad.Calender;
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

    @NotNull
    private LocalDateTime todoAt;

    @NotNull
    private Calender calender;

    public Todo toEntity(Member member){
        return Todo.builder()
                .requestTodoDto(this)
                .member(member)
                .build();
    }

}
