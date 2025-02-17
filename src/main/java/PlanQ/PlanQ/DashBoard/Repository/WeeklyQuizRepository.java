package PlanQ.PlanQ.DashBoard.Repository;


import PlanQ.PlanQ.DashBoard.DTO.WeeklyQuizDto;
import PlanQ.PlanQ.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklyQuizRepository extends JpaRepository<Quiz,Long> {

        @Query("select new PlanQ.PlanQ.DashBoard.DTO.WeeklyQuizDto(q.questionCnt,q.correctCnt,q.questionCnt-q.correctCnt) " +
            "from Quiz q " +
            "where q.reviewDate between :startDate and :endDate")
        List<WeeklyQuizDto> findQuestionNumAndCorrectNumByDate(String startDate, String endDate);
}
