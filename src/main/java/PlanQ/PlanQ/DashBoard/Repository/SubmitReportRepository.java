package PlanQ.PlanQ.DashBoard.Repository;

import PlanQ.PlanQ.DashBoard.DTO.SubmitReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitReportRepository extends JpaRepository<SubmitReportDto,Long> {

    @Query("select new PlanQ.PlanQ.DashBoard.DTO.SubmitReportDto(t.title, r.updateDate, r.comment, r.color) " +
            "from Report r " +
            "join Todo t ON r.todo.id = t.id " +
            "where r.updateDate between :startDate and :endDate")
    List<SubmitReportDto> getWeeklySubmittedReport(String startDate, String endDate);
}
