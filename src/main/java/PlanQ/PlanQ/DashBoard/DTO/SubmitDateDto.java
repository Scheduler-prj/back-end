package PlanQ.PlanQ.DashBoard.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class SubmitDateDto {

    private final LocalDateTime updatedDate;
}
