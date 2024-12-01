package PlanQ.PlanQ.report;


import PlanQ.PlanQ.quiz.Quiz;
import PlanQ.PlanQ.todo.Todo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
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

    @Column(nullable = false, name = "file_name")
    private String fileName;

    private String comment;

    @Column(name = "is_quiz")
    private boolean isQuiz;

    @Column(nullable = false, name = "update_date")
    private LocalDateTime updateDate;

    @Column(nullable = false)
    private String url;
}
