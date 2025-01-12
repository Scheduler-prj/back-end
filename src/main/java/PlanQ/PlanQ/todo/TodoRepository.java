package PlanQ.PlanQ.todo;


import PlanQ.PlanQ.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select t " +
            "from Todo t " +
            "where t.member = :member AND FUNCTION('MONTH', t.todoAt) = :month AND FUNCTION('YEAR', t.todoAt) = :year ")
    List<Todo> findAllByMemberAndYearMonth(Member member, Long year, Long month);
}