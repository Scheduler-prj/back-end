package PlanQ.PlanQ.report.dto.request;

import PlanQ.PlanQ.report.Report;
import PlanQ.PlanQ.todo.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReportDto {
    @NotBlank
    private String fileName;
    @NotBlank
    private String comment;
    @NotNull
    private boolean isQuiz;

    public Report toEntity(String url, Todo todo){
        return Report.builder()
                .requestReportDto(this)
                .url(url)
                .todo(todo)
                .build();
    }
}
