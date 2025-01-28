package PlanQ.PlanQ.report;

//import PlanQ.PlanQ.global.S3Service;
import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.Member.MemberService;
import PlanQ.PlanQ.downloadLogs.DownloadService;
import PlanQ.PlanQ.global.S3Service;
import PlanQ.PlanQ.report.dto.request.RequestReportDto;
import PlanQ.PlanQ.report.dto.response.ResponsePdfDto;
import PlanQ.PlanQ.todo.Todo;
import PlanQ.PlanQ.todo.TodoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final TodoService todoService;
    private final S3Service s3Service;
    private final MemberService memberService;
    private final DownloadService downloadService;

    public Report findById(Long id){
        return reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 Report Entity 찾지 못함: " + id));
    }

    @Transactional
    public Report createReport(Long todoId, MultipartFile file, RequestReportDto requestReportDto){
        Todo todo = todoService.findById(todoId);
        String s3Url = s3Service.upload(file);
        System.out.println("S3 URL: " + s3Url);
        Report report = requestReportDto.toEntity(s3Url, todo);
        reportRepository.save(report);
        todoService.updateIsClear(todo);
        return report;
    }

    public List<ResponsePdfDto> getPdfOfYearMonth(Long year, Long month){
        Member member = memberService.getMember();
        if(month == 0 || year == 0){
            return reportRepository.findAllByTodo_Member(member).stream()
                    .map(Report :: toResponsePdfDto)
                    .toList();
        }
        return reportRepository.findAllByTodo_Member(member, year, month).stream()
                .map(Report :: toResponsePdfDto)
                .toList();
    }

    @Transactional
    public boolean deletePdf(Long id){
        Member member = memberService.getMember();
        Report report = findById(id);
        if(!member.equals(report.getTodo().getMember())){
            log.error("삭제 불가");
            return false;
        }
        reportRepository.delete(report);
        return true;
    }

    public String getUrl(Long id){
        Report report = findById(id);
        Member member = memberService.getMember();
        downloadService.downLoad(member, report);
        return report.getUrl();
    }
}
