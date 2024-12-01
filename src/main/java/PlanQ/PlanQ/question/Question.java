package PlanQ.PlanQ.question;

import PlanQ.PlanQ.option.Option;
import PlanQ.PlanQ.quiz.Quiz;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private String title;

    @Enumerated(EnumType.STRING)
    private QuestionCategory questionCategory;

    private String correct;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @Column(nullable = false)
    private Long selectOption;

    @Column(nullable = false)
    private Long num;

}
