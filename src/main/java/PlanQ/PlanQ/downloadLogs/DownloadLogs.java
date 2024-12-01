package PlanQ.PlanQ.downloadLogs;

import PlanQ.PlanQ.report.Report;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private Long userId;

    private LocalDateTime dateTime;

    private String status;



}
