package PlanQ.PlanQ.question;

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
    private Integer questionNum; // num -> question_num

    @Column(nullable = false)
    private String content; // title -> content

    @Column(nullable = false)
    private String correct;

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
}
