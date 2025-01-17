package PlanQ.PlanQ.downloadLogs;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.report.Report;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "downloadLogs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DownloadLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdf_id")
    private Report report;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime dateTime;


    @Builder
    public DownloadLogs(Member member, Report report){
        this.report = report;
        this.member = member;
        this.dateTime = LocalDateTime.now();
    }

    public void updateDate(){
        this.dateTime = LocalDateTime.now();
    }

}
