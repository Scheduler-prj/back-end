package PlanQ.PlanQ.todo;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.todo.dto.request.RequestTodoDto;
import PlanQ.PlanQ.todo.dto.response.ResponseTodoDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private String title;

    private LocalDateTime todoAt;

    @Enumerated(EnumType.STRING)
    private Color color;

    private boolean alarm;

    private String comment;

    @Column(name = "is_clear")
    private boolean isClear;

    @Builder
    public Todo(RequestTodoDto requestTodoDto, Member member, Color color){
        this.member = member;
        this.title = requestTodoDto.getTitle();
        this.todoAt = requestTodoDto.getTodoAt();
        this.color = color;
        this.alarm = requestTodoDto.isPlanAlarm();
        this.comment = requestTodoDto.getPlanComment();
        this.isClear = false;
    }

    public Todo edit(RequestTodoDto requestTodoDto, Color color){
        this.title = requestTodoDto.getTitle();
        this.todoAt = requestTodoDto.getTodoAt();
        this.color = color;
        this.alarm = requestTodoDto.isPlanAlarm();
        this.comment = requestTodoDto.getPlanComment();
        return this;
    }

    public ResponseTodoDto toResponseTotoDto(){
        return new ResponseTodoDto(
                this.id,
                this.title,
                this.todoAt,
                this.color.toString(),
                this.alarm,
                this.comment
        );
    }

    public void updateIsClear(){
        this.isClear = true;
    }
}
