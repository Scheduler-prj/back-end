package PlanQ.PlanQ.todo;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.embeddad.Calender;
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

    private LocalDateTime todoAt;

    @Embedded
    private Calender calender;

    @Builder
    public Todo(RequestTodoDto requestTodoDto, Member member){
        this.member = member;
        this.todoAt = requestTodoDto.getTodoAt();
        this.calender = requestTodoDto.getCalender();
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
                this.getCalender().getColor().toString(),
                this.getCalender().isAlarm(),
                this.getCalender().isClear()
        );
    }

    public void updateIsClear(){
        this.calender.changeClear();
    }
}
