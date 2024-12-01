package PlanQ.PlanQ.notification;

import PlanQ.PlanQ.plan.Plan;
import PlanQ.PlanQ.routine.Routine;
import PlanQ.PlanQ.todo.Todo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "notification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column
    private String status;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "is_read")
    private boolean isRead;

    @OneToOne
    @JoinColumn(name = "planId", nullable = true)
    private Plan plan;

    @OneToOne
    @JoinColumn(name = "routineId", nullable = true)
    private Routine routine;

    @OneToOne
    @JoinColumn(name = "todoId", nullable = true)
    private Todo todo;


}
