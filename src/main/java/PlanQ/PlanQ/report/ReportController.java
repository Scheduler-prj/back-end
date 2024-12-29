package PlanQ.PlanQ.report;

import PlanQ.PlanQ.report.dto.request.RequestReportDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @Operation(summary = "리포트 업로드", description = "리포트 업로드")
    @PostMapping("/todo/{todoId}")
    public ResponseEntity<Long> uploadReport(@PathVariable Long todoId,
                                             @RequestPart(value = "file", required = false) MultipartFile file,
                                             @RequestPart(value = "info") @Validated RequestReportDto requestReportDto){
        Long response = reportService.createReport(todoId, file, requestReportDto);
        if(response == null){
            return ResponseEntity.badRequest().body(0L);
        }
        return ResponseEntity.ok(response);
    }
}
