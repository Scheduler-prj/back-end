package PlanQ.PlanQ.report;

import PlanQ.PlanQ.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByTodo_Member(Member member);

    @Query("select r " +
            "from Report r " +
            "where r.todo.member = :member AND FUNCTION('MONTH', r.updateDate) = :month AND FUNCTION('YEAR', r.updateDate) = :year ")
    List<Report> findAllByTodo_Member(Member member, Long year ,Long month);
}
