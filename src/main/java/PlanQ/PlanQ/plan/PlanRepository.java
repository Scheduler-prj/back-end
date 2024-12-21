package PlanQ.PlanQ.plan;

import PlanQ.PlanQ.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query("SELECT p " +
            "FROM Plan p " +
            "WHERE p.member = :member AND (FUNCTION('MONTH', p.startDate) = :month OR FUNCTION('MONTH', p.endDate) = :month)")
    List<Plan> findAllByMemberAndMonth(Member member, Long month);
}
