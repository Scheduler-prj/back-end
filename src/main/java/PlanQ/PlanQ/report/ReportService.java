package PlanQ.PlanQ.report;

//import PlanQ.PlanQ.global.S3Service;
import PlanQ.PlanQ.report.dto.request.RequestReportDto;
import PlanQ.PlanQ.todo.Todo;
import PlanQ.PlanQ.todo.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final TodoService todoService;
    //private final S3Service s3Service;

    @Transactional
    public Long createReport(Long todoId, MultipartFile file, RequestReportDto requestReportDto){
        Todo todo = todoService.findById(todoId);
        String s3Url = "dummy";
        //String s3Url = s3Service.upload(file);
        Report report = requestReportDto.toEntity(s3Url, todo);
        reportRepository.save(report);
        todoService.updateIsClear(todo);
        return report.getId();
    }
}
