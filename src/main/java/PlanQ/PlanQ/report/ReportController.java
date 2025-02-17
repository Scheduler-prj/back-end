package PlanQ.PlanQ.report;

import PlanQ.PlanQ.quiz.QuizService;
import PlanQ.PlanQ.report.dto.request.RequestReportDto;
import PlanQ.PlanQ.report.dto.response.ResponsePdfDto;
import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final QuizService quizService;

    @Operation(summary = "리포트 업로드", description = "리포트 업로드")
    @PostMapping("/todo/{todoId}")
    public ResponseEntity<Long> uploadReport(@PathVariable Long todoId,
                                             @RequestPart(value = "file", required = false) MultipartFile file,
                                             @RequestPart(value = "info") @Validated RequestReportDto requestReportDto) throws IOException {
        Report report = reportService.createReport(todoId, file, requestReportDto);
        quizService.generateQuizFromPdf(file, report);
        if(report == null){
            return ResponseEntity.badRequest().body(0L);
        }
        return ResponseEntity.ok(report.getId());
    }

    @Operation(summary = "리포트 달별로 확인 Month의 값을 0으로 줄시 모든 리포트 반환", description = "리포트 달별 확인")
    @GetMapping("/pdf/{year}/{month}")
    public ResponseEntity<List<ResponsePdfDto>> viewAllPdfYearMonth(@PathVariable Long year, @PathVariable Long month){
        List<ResponsePdfDto> responsePdfDtoList = reportService.getPdfOfYearMonth( year, month);
        return ResponseEntity.ok(responsePdfDtoList);
    }

    @Operation(summary = "리포트 삭제", description = "리포트 삭제")
    @DeleteMapping("/pdf/{id}")
    public ResponseEntity<Boolean> deletePdf(@PathVariable Long id){
        boolean response = reportService.deletePdf(id);
        if(response){
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(false);
        }
    }

    @Operation(summary = "리포트 Url반환", description = "리포트 Url반환")
    @GetMapping("/pdf/download/{id}")
    public ResponseEntity<String> responseUrl(@PathVariable Long id){
        String response = reportService.getUrl(id);
        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body("url이 존재하지 않음");
        }
    }

}
