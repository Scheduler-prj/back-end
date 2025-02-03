package PlanQ.PlanQ.quiz;

import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.question.dto.response.ResponseQuestionDto;
import PlanQ.PlanQ.quiz.dto.response.ResponseQuizDto;
import PlanQ.PlanQ.report.Report;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;

@Data
@Entity
@Builder
@Table(name = "quiz")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime reviewDate; // date -> reviewDate

    private LocalTime solveTime; // time -> solveTime

    private String category; // 디자인 회의 필요(직접 지정 or to do 제목)

    private Integer correctCnt; // correctNum -> correctCnt

    @Column(nullable = false)
    private Integer questionCnt; // questionNum -> questionCnt

    @Builder.Default
    @Column(nullable = false)
    private boolean isSolved = false;

    @Builder.Default
    @Column(nullable = false)
    private boolean favorite = false;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    public ResponseQuizDto toResponseQuizDto(List<ResponseQuestionDto> questionList){
        return new ResponseQuizDto(
                this.id,
                this.title,
                this.reviewDate,
                this.solveTime,
                this.category,
                this.correctCnt,
                this.questionCnt,
                this.isSolved,
                this.favorite,
                this.color != null ? this.color.toString() : "RED",
                questionList
        );
    }

    public void changeCorrectNum(Integer correctCnt){
        this.correctCnt = correctCnt;
    }

    public void clearSolved(){
        this.isSolved = true;
        this.solveTime = LocalTime.now();
    }
}
