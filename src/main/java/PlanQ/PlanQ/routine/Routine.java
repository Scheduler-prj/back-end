package PlanQ.PlanQ.routine;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.routine.dto.request.RequestRoutineDto;
import PlanQ.PlanQ.routine.dto.response.ResponseRoutineDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "routine")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    @Column
    private String title;



    @ElementCollection(targetClass = Dotw.class)
    @CollectionTable(name = "dotws", joinColumns = @JoinColumn(name = "routine_id")
    )
    @Enumerated(value = EnumType.STRING)
    private List<Dotw> dotws;

    @Column
    private boolean alarm;

    @Column
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private boolean isClear;

    @Builder
    public Routine(RequestRoutineDto requestRoutineDto, Member member, List<Dotw> dotws){
        this.title = requestRoutineDto.getTitle();
        this.dotws = dotws;
        this.alarm = requestRoutineDto.isAlarm();
        this.comment = requestRoutineDto.getComment();
        this.member = member;
        this.isClear = false;
    }

    public ResponseRoutineDto toResponseRoutineDto(){
        return new ResponseRoutineDto(
                this.id,
                this.title,
                this.dotws.stream().map(Dotw :: changeString).toList(),
                this.alarm,
                this.comment,
                this.isClear
        );
    }

    public void edit(RequestRoutineDto requestRoutineDto){
        this.title = requestRoutineDto.getTitle();
        this.dotws = requestRoutineDto.changeEnum(requestRoutineDto.getDotws());
        this.alarm = requestRoutineDto.isAlarm();
        this.comment = requestRoutineDto.getComment();
        this.isClear = false;
    }

    public void changeClear(){
        this.isClear = !this.isClear;
    }

    public void reset(){
        this.isClear = false;
    }

}
