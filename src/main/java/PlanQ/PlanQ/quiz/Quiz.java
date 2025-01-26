package PlanQ.PlanQ.quiz;

import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.report.Report;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "quiz")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    private String title;

    private LocalDateTime reviewDate; // date -> reviewDate

    private LocalTime solveTime; // time -> solveTime

    private String category; // 디자인 회의 필요(직접 지정 or to do 제목)

    private int correctCnt; // correctNum -> correctCnt

    private int questionCnt; // questionNum -> questionCnt

    @Builder.Default
    private boolean isSolved = false;

    @Builder.Default
    @Column(nullable = false)
    private boolean favorite = false;

    @Enumerated(EnumType.STRING)
    private Color color;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;
}
