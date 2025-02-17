package PlanQ.PlanQ.quiz;

import PlanQ.PlanQ.quiz.dto.request.RequestSolveDto;
import PlanQ.PlanQ.quiz.dto.response.ResponseQuizDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @Operation(summary = "퀴즈 문제 조회", description = "퀴즈 문제 조회")
    @GetMapping("/{quiz_id}")
    public ResponseEntity<ResponseQuizDto> viewQuizStart(@PathVariable(name = "quiz_id") Long quizId){
        ResponseQuizDto responseQuizDto = quizService.findQuizData(quizId);
        return ResponseEntity.ok(responseQuizDto);
    }

    @Operation(summary = "퀴즈 문제 풀이 제출", description = "퀴즈 문제 풀이 제출\n여기서 solveTime은 스웨거에서 제공하는 input데이터와 다르게 solveTime\": \"00:10:01\" 로 해줘야댐")
    @PostMapping("/{quiz_id}")
    public ResponseEntity<Boolean> postQuizSolve(@PathVariable(name = "quiz_id") Long quizId, @RequestBody RequestSolveDto requestSolveDto){
        boolean response = quizService.solvedQuiz(quizId, requestSolveDto);
        return ResponseEntity.ok(response);
    }

}
