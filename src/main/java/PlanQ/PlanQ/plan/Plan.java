package PlanQ.PlanQ.plan;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.embeddad.Calender;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.notification.Notification;
import PlanQ.PlanQ.notification.Type;
import PlanQ.PlanQ.plan.dto.request.RequestPlanDto;
import PlanQ.PlanQ.plan.dto.response.ResponsePlanDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "plan")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @Embedded
    private Calender calender;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public Plan(Member member, RequestPlanDto requestPlanDto){
        this.calender = requestPlanDto.getCalender();
        this.color = requestPlanDto.getColor();
        this.startDate = requestPlanDto.getStartDate();
        this.endDate = requestPlanDto.getEndDate();
        this.member = member;
    }

    public void update(RequestPlanDto requestPlanDto){
        this.calender = requestPlanDto.getCalender();
        this.startDate = requestPlanDto.getStartDate();
        this.endDate = requestPlanDto.getEndDate();
    }

    public void clear(){
        this.calender.checkClear();
    }

    public ResponsePlanDto toResponsePlanDto(){
        return new ResponsePlanDto(
                this.id,
                this.calender.getTitle(),
                this.startDate,
                this.endDate,
                this.color.toString(),
                this.calender.isAlarm(),
                this.calender.getComment(),
                this.calender.isClear()
        );
    }

    public Notification toNotification(){
        return Notification.builder()
                .type(Type.PLAN)
                .plan(this)
                .todo(null)
                .routine(null)
                .build();
    }
}
