package PlanQ.PlanQ.routine;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.embeddad.Calender;
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

    @Embedded
    private Calender calender;

    @ElementCollection(targetClass = Dotw.class)
    @CollectionTable(name = "dotws", joinColumns = @JoinColumn(name = "routine_id")
    )
    @Enumerated(value = EnumType.STRING)
    private List<Dotw> dotws;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;


    @Builder
    public Routine(RequestRoutineDto requestRoutineDto, Member member, List<Dotw> dotws){
        this.dotws = dotws;
        this.calender = requestRoutineDto.getCalender();
        this.member = member;
    }

    public ResponseRoutineDto toResponseRoutineDto(){
        return new ResponseRoutineDto(
                this.id,
                this.calender.getTitle(),
                this.dotws.stream().map(Dotw :: changeString).toList(),
                this.calender.isAlarm(),
                this.calender.getComment(),
                this.calender.isClear()
        );
    }

    public void edit(RequestRoutineDto requestRoutineDto){
        this.dotws = requestRoutineDto.changeEnum(requestRoutineDto.getDotws());
        this.calender = requestRoutineDto.getCalender();
    }

    public void changeClear(){
        this.calender.changeClear();
    }

    public void reset(){
        this.calender.reset();
    }

}
