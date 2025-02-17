package PlanQ.PlanQ.routine;

import PlanQ.PlanQ.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findAllByMember(Member member);

    @Query("select r from Routine r " +
            "join r.dotws d " +
            "where d = :dotw " +
            "and r.calender.isClear = false")
    List<Routine> findAllByCalenderFalse(Dotw dotw);
}
