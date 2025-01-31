package PlanQ.PlanQ.notification;

import PlanQ.PlanQ.plan.Plan;
import PlanQ.PlanQ.routine.Routine;
import PlanQ.PlanQ.todo.Todo;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "planId", nullable = true)
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "routineId", nullable = true)
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "todoId", nullable = true)
    private Todo todo;

    @Builder
    public Notification(Type type, Plan plan, Routine routine, Todo todo){
        this.type = type;

        if(type == Type.PLAN && plan != null){
            this.plan = plan;
            this.status = plan.getCalender().getTitle() + "이 아직 완료 되지 않았습니다.";
        }
        if(type == Type.ROUTINE && routine != null){
            this.routine = routine;
            this.status = routine.getCalender().getTitle() + "이 아직 완료 되지 않았습니다.";
        }
        if(type == Type.TODO && todo != null){
            this.todo = todo;
            this.status = todo.getCalender().getTitle() + "이 아직 완료 되지 않았습니다.";
        }

        this.isRead = false;
    }

    public void readIt(){
        this.isRead = true;
    }

    public ResponseNotificationDto toResponse(){
        return new ResponseNotificationDto(
                this.id,
                this.status,
                this.type,
                this.isRead
        );
    }
}
