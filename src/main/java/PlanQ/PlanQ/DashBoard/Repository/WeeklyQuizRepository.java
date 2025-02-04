package PlanQ.PlanQ.DashBoard.Repository;


import PlanQ.PlanQ.DashBoard.DTO.WeeklyQuizDto;
import PlanQ.PlanQ.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeeklyQuizRepository extends JpaRepository<Quiz,Long> {

    @Query("SELECT new PlanQ.PlanQ.DashBoard.DTO.WeeklyQuizDto( " +
       " q.questionCnt, q.correctCnt, " +
       " CASE DAYOFWEEK(q.solveDate) " +
       "   WHEN 1 THEN '일' " +
       "   WHEN 2 THEN '월' " +
       "   WHEN 3 THEN '화' " +
       "   WHEN 4 THEN '수' " +
       "   WHEN 5 THEN '목' " +
       "   WHEN 6 THEN '금' " +
       "   WHEN 7 THEN '토' " +
       " END) AS dotw " +
       " FROM Quiz q " +
       "join Report r on q.report.id = r.id " +
       "join Todo t on r.id = t.report.id " +
       "join Member m on t.member.id = m.id " +
       " WHERE q.solveDate BETWEEN :startDate AND :endDate and m.id = :memberId ")
        List<WeeklyQuizDto> findQuestionNumAndCorrectNumByDate(String startDate, String endDate, Long memberId);











}
