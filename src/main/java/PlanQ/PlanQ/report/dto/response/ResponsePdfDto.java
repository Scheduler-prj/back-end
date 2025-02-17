package PlanQ.PlanQ.report.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePdfDto {
    private Long pdfId;
    private String fileName;
    private LocalDateTime uploadDate;
    private boolean isQuiz;
}
