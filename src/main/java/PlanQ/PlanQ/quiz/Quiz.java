package PlanQ.PlanQ.quiz;

import PlanQ.PlanQ.question.Question;
import PlanQ.PlanQ.report.Report;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "quiz")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;

    private String title;

    private String category;

    private LocalDateTime date;

    @Column(name = "is_solved")
    private boolean isSolved;

    private LocalDateTime time;

    @Column(name = "correct_num")
    private Long correctNum;

    @Column(name = "question_num")
    private Long questionNum;

    private boolean favorite;
}
