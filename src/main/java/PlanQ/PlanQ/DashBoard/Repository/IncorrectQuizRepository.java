package PlanQ.PlanQ.DashBoard.Repository;

import PlanQ.PlanQ.DashBoard.DTO.IncorrectQuestionDto;
import PlanQ.PlanQ.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncorrectQuizRepository extends JpaRepository<Quiz,Long> {

    @Query("select new PlanQ.PlanQ.DashBoard.DTO.IncorrectQuestionDto(qu.color, qu.title, q.questionNum, q.content) " +
            "from Question q " +
            "join Quiz qu on qu.id = q.quiz.id " +
            "where qu.reviewDate between :startDate and :endDate")
    List<IncorrectQuestionDto> findIncorrectQuestionDtosByDate(String startDate, String endDate);
}
