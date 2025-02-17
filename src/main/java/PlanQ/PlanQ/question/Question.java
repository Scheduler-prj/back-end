package PlanQ.PlanQ.question;

import PlanQ.PlanQ.option.dto.response.ResponseOptionDto;
import PlanQ.PlanQ.question.dto.response.ResponseQuestionDto;
import PlanQ.PlanQ.quiz.Quiz;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Setter
@Builder
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer questionNum = 0; // num -> question_num

    @Column(nullable = false)
    private String content; // title -> content

    @Column(nullable = false)
    private Integer correct; // String -> Integer 변경

    private Integer selectOption; // (long -> Integer(null 허용을 위해)

    @Builder.Default
    @Column(nullable = false)
    private boolean isCorrect = false;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private QuestionType questionType = QuestionType.MCQ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    public ResponseQuestionDto toResponseQuestionDto(List<ResponseOptionDto> options){
        return new ResponseQuestionDto(
                this.id,
                this.questionNum,
                this.content,
                this.correct,
                this.selectOption,
                this.isCorrect,
                this.questionType.toString(),
                options
        );
    }

    public boolean checkCorrect(Integer selectOption){
        this.selectOption = selectOption;
        this.isCorrect = this.selectOption.equals(correct);
        return this.isCorrect;
    }
}
