package PlanQ.PlanQ.routine;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.routine.dto.request.RequestRoutineDto;
import PlanQ.PlanQ.routine.dto.response.ResponseRoutineDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

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

    @Column
    private String dotws;

    @Column
    private boolean alarm;

    @Column
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public Routine(RequestRoutineDto requestRoutineDto, Member member){
        ObjectMapper objectMapper = new ObjectMapper();
        this.title = requestRoutineDto.getTitle();
        try {
            this.dotws =  objectMapper.writeValueAsString(requestRoutineDto.getDotw());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.alarm = requestRoutineDto.isAlarm();
        this.comment = requestRoutineDto.getComment();
        this.member = member;
    }

    /*public ResponseRoutineDto toResponseRoutineDto(){
        return new ResponseRoutineDto(
                this.id,
                this.title,
                this.dotws,
                this.alarm,
                this.comment
        );
    }*/

}
