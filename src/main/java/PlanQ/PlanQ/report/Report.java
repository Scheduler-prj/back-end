package PlanQ.PlanQ.report;


import PlanQ.PlanQ.downloadLogs.DownloadLogs;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.quiz.Quiz;
import PlanQ.PlanQ.report.dto.request.RequestReportDto;
import PlanQ.PlanQ.report.dto.response.ResponsePdfDto;
import PlanQ.PlanQ.todo.Todo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "report")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id",nullable = false)
    private Todo todo;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DownloadLogs> DownloadLogsList = new ArrayList<>();

    @Column(nullable = false, name = "file_name")
    private String fileName;

    private String comment;

    @Column(name = "is_quiz")
    private boolean isQuiz;

    @CreatedDate
    @LastModifiedDate
    @Column(nullable = false, name = "update_date")
    private LocalDateTime updateDate;

    @Column(nullable = false, length = 2048)
    private String url;

    @Builder
    public Report(RequestReportDto requestReportDto, String url, Todo todo){
        this.todo = todo;
        this.fileName = requestReportDto.getFileName();
        this.comment = requestReportDto.getComment();
        this.isQuiz = requestReportDto.isQuiz();
        this.updateDate = LocalDateTime.now();
        this.url = url;
    }

    private Color color;


    public ResponsePdfDto toResponsePdfDto(){
        return new ResponsePdfDto(
                this.id,
                this.fileName,
                this.updateDate,
                this.isQuiz
        );
    }

}
