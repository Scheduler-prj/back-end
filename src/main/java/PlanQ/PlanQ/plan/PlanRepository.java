package PlanQ.PlanQ.plan;

import PlanQ.PlanQ.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query("SELECT p " +
            "FROM Plan p " +
            "WHERE p.member = :member AND (FUNCTION('MONTH', p.startDate) = :month OR FUNCTION('MONTH', p.endDate) = :month) " +
            "AND FUNCTION('YEAR', p.startDate) = :year OR FUNCTION('YEAR', p.endDate) = :year")
    List<Plan> findAllByMemberAndYearMonth(Member member, Long year, Long month);
}
