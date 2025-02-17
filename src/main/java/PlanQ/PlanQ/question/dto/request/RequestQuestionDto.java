package PlanQ.PlanQ.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestQuestionDto {
    private Long id;
    private Integer selectOption;
}
