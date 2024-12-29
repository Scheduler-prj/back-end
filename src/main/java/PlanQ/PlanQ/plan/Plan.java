package PlanQ.PlanQ.plan;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.global.Color;
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

    @Column
    private String title;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column
    private boolean Alarm;

    @Column
    private String Comment;

    @Column(name = "is_clear")
    private boolean isClear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public Plan(Member member, RequestPlanDto requestPlanDto){
        this.title = requestPlanDto.getTitle();
        this.startDate = requestPlanDto.getStartDate();
        this.endDate = requestPlanDto.getEndDate();
        this.color = Color.valueOf(requestPlanDto.getColor());
        this.Alarm = requestPlanDto.isAlarm();
        this.Comment = requestPlanDto.getComment();
        this.isClear = false;
        this.member = member;
    }

    public void update(RequestPlanDto requestPlanDto){
        this.title = requestPlanDto.getTitle();
        this.startDate = requestPlanDto.getStartDate();
        this.endDate = requestPlanDto.getEndDate();
        this.color = Color.valueOf(requestPlanDto.getColor());
        this.Alarm = requestPlanDto.isAlarm();
        this.Comment = requestPlanDto.getComment();
        this.isClear = false;
    }

    public void clear(){
        this.isClear = true;
    }

    public ResponsePlanDto toResponsePlanDto(){
        return new ResponsePlanDto(
                this.id,
                this.title,
                this.startDate,
                this.endDate,
                this.color.toString(),
                this.Alarm,
                this.Comment,
                this.isClear
        );
    }
}
