package PlanQ.PlanQ.todo;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.embeddad.Calender;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.notification.Notification;
import PlanQ.PlanQ.notification.Type;
import PlanQ.PlanQ.report.Report;
import PlanQ.PlanQ.todo.dto.request.RequestTodoDto;
import PlanQ.PlanQ.todo.dto.response.ResponseTodoDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    private LocalDateTime todoAt;

    @Embedded
    private Calender calender;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Builder
    public Todo(RequestTodoDto requestTodoDto, Member member){
        this.member = member;
        this.todoAt = requestTodoDto.getTodoAt();
        this.calender = requestTodoDto.getCalender();
        this.color = requestTodoDto.getColor();
    }

    public Todo edit(RequestTodoDto requestTodoDto){
        this.todoAt = requestTodoDto.getTodoAt();
        this.calender = requestTodoDto.getCalender();
        return this;
    }

    public ResponseTodoDto toResponseTotoDto(){
        return new ResponseTodoDto(
                this.id,
                this.getCalender().getTitle(),
                this.getCalender().getComment(),
                this.todoAt,
                this.getColor().toString(),
                this.getCalender().isAlarm(),
                this.getCalender().isClear()
        );
    }

    public void updateIsClear(){
        this.calender.changeClear();
    }

    public Notification toNotification(){
        return Notification.builder()
                .type(Type.TODO)
                .plan(null)
                .todo(this)
                .routine(null)
                .build();
    }
}
