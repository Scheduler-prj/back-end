package PlanQ.PlanQ.downloadLogs;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadLogsRepository extends JpaRepository<DownloadLogs, Long> {
    DownloadLogs findByMemberAndReport(Member member, Report report);
}
