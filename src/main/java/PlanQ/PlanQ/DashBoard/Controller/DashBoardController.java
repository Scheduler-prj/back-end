package PlanQ.PlanQ.DashBoard.Controller;

import PlanQ.PlanQ.DashBoard.DTO.DailyQuizDto;
import PlanQ.PlanQ.DashBoard.DTO.IncorrectQuestionDto;
import PlanQ.PlanQ.DashBoard.DTO.SubmitReportDto;
import PlanQ.PlanQ.DashBoard.DTO.WeeklyQuizDto;
import PlanQ.PlanQ.DashBoard.Service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.Lint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashBoardController {

    private final DashBoardService dashBoardService;

    // 1. 오늘 만들어진 퀴즈 조회
    @GetMapping("/dailyquiz/{date}") // 날짜형식 : yyyy-mm-dd
    public ResponseEntity<List<DailyQuizDto>> getDailyQuiz(@PathVariable String date){
        return ResponseEntity.ok(dashBoardService.getDailyQuizzes(date));
    }

    // 2. 퀴즈 분석
    @GetMapping("/weeklyquiz/{date}")
    public ResponseEntity<List<WeeklyQuizDto>> getWeeklyQuiz(@PathVariable String date){
        return ResponseEntity.ok(dashBoardService.getWeeklyQuizzesNum(date));
    }

    // 3. 틀린 문항 보기
    @GetMapping("/incorrectQuiz/{date}")
    public ResponseEntity<List<IncorrectQuestionDto>> getIncorrectQuiz(@PathVariable String date){
        return ResponseEntity.ok(dashBoardService.getIncorrectQuiz(date));
    }

    // 4. 복습 날짜 조회

    // 5. 제출된 성과 조회
    @GetMapping("/submittedReport/{date}")
    public  ResponseEntity<List<SubmitReportDto>> getWeeklySubmittedReport(@PathVariable String date){
        return ResponseEntity.ok(dashBoardService.getWeeklySubmittedReport(date));
    }

}
