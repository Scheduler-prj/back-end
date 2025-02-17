package PlanQ.PlanQ.plan;

import PlanQ.PlanQ.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query("SELECT p " +
            "FROM Plan p " +
            "WHERE p.member = :member " +
            "AND p.calender.alarm = true " +
            "AND p.startDate <= :today " +
            "AND p.endDate >= :today ")
    List<Plan> findAllByMemberAndCalender_AlarmTrueAndCalender_ClearFalseAndToday(Member member, LocalDateTime today);

    @Query("select p " +
            "from Plan p " +
            "where p.calender.isClear = false " +
            "AND p.calender.alarm = true " +
            "AND p.startDate <= :today " +
            "AND p.endDate >= :today ")
    List<Plan> findAllByCalenderIsFalse(LocalDateTime today);
}
