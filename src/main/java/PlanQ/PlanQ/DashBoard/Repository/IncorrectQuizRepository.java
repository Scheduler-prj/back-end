package PlanQ.PlanQ.DashBoard.Repository;

import PlanQ.PlanQ.DashBoard.DTO.IncorrectQuestionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncorrectQuizRepository extends JpaRepository<IncorrectQuizRepository,Long> {

    @Query("select new PlanQ.PlanQ.DashBoard.DTO.IncorrectQuestionDto(qu.color, qu.title, q.num, q.title) " +
            "from Question q " +
            "join Quiz qu on qu.id = q.quiz.id " +
            "where qu.date between :startDate and :endDate")
    List<IncorrectQuestionDto> findIncorrectQuestionDtosByDate(String startDate, String endDate);
}
