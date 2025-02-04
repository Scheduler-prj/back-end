package PlanQ.PlanQ.DashBoard.Service;

import PlanQ.PlanQ.DashBoard.DTO.DailyQuizDto;
import PlanQ.PlanQ.DashBoard.DTO.IncorrectQuestionDto;
import PlanQ.PlanQ.DashBoard.DTO.SubmitReportDto;
import PlanQ.PlanQ.DashBoard.DTO.WeeklyQuizDto;
import PlanQ.PlanQ.DashBoard.Repository.DailyQuizRepository;
import PlanQ.PlanQ.DashBoard.Repository.IncorrectQuizRepository;
import PlanQ.PlanQ.DashBoard.Repository.SubmitReportRepository;
import PlanQ.PlanQ.DashBoard.Repository.WeeklyQuizRepository;
import PlanQ.PlanQ.Member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DashBoardService {

    private final DailyQuizRepository dailyQuizRepository;
    private final WeeklyQuizRepository weeklyQuizRepository;
    private final IncorrectQuizRepository incorrectQuizRepository;
    private final SubmitReportRepository submitReportRepository;
    private final MemberService memberService;

    public List<DailyQuizDto> getDailyQuizzes(String date) {
        Long memberId = memberService.getMember().getId();
        return dailyQuizRepository.findDailyQuizDtosByDate(date,memberId);
    }

    public List<WeeklyQuizDto> getWeeklyQuizzesNum(String date){
        List<String> dates = getAfterSevenDay(date);
        Long memberId = memberService.getMember().getId();
        List<WeeklyQuizDto> weeklyQuizDtos = weeklyQuizRepository.findQuestionNumAndCorrectNumByDate(dates.get(0), dates.get(1), memberId);
        return weeklyQuizDtos;
    }

    public List<IncorrectQuestionDto> getIncorrectQuiz(String date){
        List<String> dates = getAfterSevenDay(date);
        Long memberId = memberService.getMember().getId();
        List<IncorrectQuestionDto> incorrectQuestionDtos =
                incorrectQuizRepository.findIncorrectQuestionDtosByDate(dates.get(0), dates.get(1), memberId);
        return incorrectQuestionDtos;
    }

    public List<SubmitReportDto> getWeeklySubmittedReport(String date){
        List<String> dates = getAfterSevenDay(date);
        Long memberId = memberService.getMember().getId();
        return submitReportRepository.getWeeklySubmittedReport(dates.get(0), dates.get(1), memberId);
    }

    // 받은 날짜 기준 7일 받아오기
    public List<String> getSevenDays(String date) {
        List<String> dates = new ArrayList<>();
        LocalDate start = LocalDate.parse(date);

        for (int i = 0; i < 7; i++) {
            dates.add(start.plusDays(i).toString());
        }
        return dates;
    }

    // [선택한 일자, 7일뒤] 형태로 리스트 반환 (비트윈 쓰려고)
    public List<String> getAfterSevenDay(String date){
        List<String> dates = new ArrayList<>();
        LocalDate start = LocalDate.parse(date);
        dates.add(start.toString());
        dates.add(start.plusDays(6).toString());

        return dates;
    }

//    // 복습 날짜 조회(매일 00시에 오늘 기준으로 며칠 연속 복습했는지 (성과 제출, 퀴즈 풀이 날짜)
//    public Integer getConsecutiveDays(String date){
//        Long memberId = memberService.getMember().getId();
//        LocalDate today = LocalDate.now();
//    }

}
