package PlanQ.PlanQ.DashBoard.Repository;

import PlanQ.PlanQ.DashBoard.DTO.SubmitReportDto;
import PlanQ.PlanQ.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitReportRepository extends JpaRepository<Report,Long> {

    @Query("select new PlanQ.PlanQ.DashBoard.DTO.SubmitReportDto(t.calender.title, r.updateDate, r.comment, r.color) " +
            "from Report r " +
            "join Todo t ON r.todo.id = t.id " +
            "join Member m on t.member.id = m.id " +
            "where r.updateDate between :startDate and :endDate and m.id = :memberId")
    List<SubmitReportDto> getWeeklySubmittedReport(String startDate, String endDate, Long memberId);


}
