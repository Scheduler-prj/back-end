package PlanQ.PlanQ.DashBoard.Service;

import PlanQ.PlanQ.DashBoard.DTO.DailyQuizDto;
import PlanQ.PlanQ.DashBoard.DTO.IncorrectQuestionDto;
import PlanQ.PlanQ.DashBoard.DTO.SubmitReportDto;
import PlanQ.PlanQ.DashBoard.DTO.WeeklyQuizDto;
import PlanQ.PlanQ.DashBoard.Repository.DailyQuizRepository;
import PlanQ.PlanQ.DashBoard.Repository.IncorrectQuizRepository;
import PlanQ.PlanQ.DashBoard.Repository.SubmitReportRepository;
import PlanQ.PlanQ.DashBoard.Repository.WeeklyQuizRepository;
import jakarta.servlet.MultipartConfigElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashBoardService {

    private final DailyQuizRepository dailyQuizRepository;
    private final WeeklyQuizRepository weeklyQuizRepository;
    private final IncorrectQuizRepository incorrectQuizRepository;
    private final SubmitReportRepository submitReportRepository;
    private final StringHttpMessageConverter stringHttpMessageConverter;
    private final MultipartConfigElement multipartConfigElement;
    private final DelegatingFilterProxyRegistrationBean securityFilterChainRegistration;

    public List<DailyQuizDto> getDailyQuizzes(String date) {
        return dailyQuizRepository.findDailyQuizDtosByDate(date);
    }

    public List<WeeklyQuizDto> getWeeklyQuizzesNum(String date){
        List<String> dates = getAfterSevenDay(date);
        List<WeeklyQuizDto> weeklyQuizDtos = weeklyQuizRepository.findQuestionNumAndCorrectNumByDate(dates.get(0), dates.get(1));
        return weeklyQuizDtos;
    }

    public List<IncorrectQuestionDto> getIncorrectQuiz(String date){
        List<String> dates = getAfterSevenDay(date);
        List<IncorrectQuestionDto> incorrectQuestionDtos =
                incorrectQuizRepository.findIncorrectQuestionDtosByDate(dates.get(0), dates.get(1));
        return incorrectQuestionDtos;
    }

    public List<SubmitReportDto> getWeeklySubmittedReport(String date){
        List<String> dates = getAfterSevenDay(date);
        return submitReportRepository.getWeeklySubmittedReport(dates.get(0), dates.get(1));
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

    // [오늘, 7일뒤] 형태로 리스트 반환 (비트윈 쓰려고)
    public List<String> getAfterSevenDay(String date){
        List<String> dates = new ArrayList<>();
        LocalDate start = LocalDate.parse(date);
        dates.add(start.toString());
        dates.add(start.plusDays(6).toString());

        return dates;
    }

}
