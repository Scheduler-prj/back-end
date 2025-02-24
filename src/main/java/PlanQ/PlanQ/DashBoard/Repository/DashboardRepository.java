package PlanQ.PlanQ.DashBoard.Repository;

import PlanQ.PlanQ.DashBoard.DTO.SubmitDateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Long,Long> {

    @Query("select q.id from Quiz q " +
            "join Report r on r.id = q.report.id " +
            "join Todo t on t.report.id = r.id " +
            "join Member m on m.id = t.member.id " +
            "where m.id =: memberId and q.solveDate =: localDateTime")
    Long findBySolvedDate(Long memberId, LocalDateTime localDateTime);

    @Query("select new PlanQ.PlanQ.DashBoard.DTO.SubmitDateDto(r.updateDate) " +
            "from Report r " +
            "join Todo t on t.report.id = r.id " +
            "join Member m on m.id = t.member.id " +
            "where m.id =:memberId ")
    List<SubmitDateDto> findReportUpdateDateByMember(@Param("memberId") Long memberId);

    @Query("select new PlanQ.PlanQ.DashBoard.DTO.SubmitDateDto(q.solveDate) " +
            "from Quiz q " +
            "join Report r on r.id = q.report.id " +
            "join Todo t on t.report.id = r.id " +
            "join Member m on m.id = t.member.id " +
            "where m.id =:memberId ")
    List<SubmitDateDto> findQuizSolvedDateByMember(@Param("memberId") Long memberId);
}
