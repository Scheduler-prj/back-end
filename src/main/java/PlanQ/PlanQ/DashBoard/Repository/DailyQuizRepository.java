package PlanQ.PlanQ.DashBoard.Repository;

import PlanQ.PlanQ.DashBoard.DTO.DailyQuizDto;
import PlanQ.PlanQ.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyQuizRepository extends JpaRepository<Quiz,Long> {
    @Query("select new PlanQ.PlanQ.DashBoard.DTO.DailyQuizDto(q.category,q.title,q.questionCnt,q.isSolved,q.color) " +
            "from Quiz q " +
            "where q.reviewDate = :date")
    List<DailyQuizDto> findDailyQuizDtosByDate(String date);
}
