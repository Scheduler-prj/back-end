package PlanQ.PlanQ.todo;


import PlanQ.PlanQ.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select t " +
            "from Todo t " +
            "where t.member = :member " +
            "AND FUNCTION('YEAR', t.todoAt) = :year " +
            "AND FUNCTION('MONTH', t.todoAt) = :month ")
    List<Todo> findAllByMemberAndYearMonth(Member member, Long year, Long month);

    List<Todo> findAllByCalender_IsClearFalseAndCalender_AlarmTrueAndTodoAtBetween(LocalDateTime start, LocalDateTime end);


}