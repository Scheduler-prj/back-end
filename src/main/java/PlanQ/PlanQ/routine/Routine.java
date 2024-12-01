package PlanQ.PlanQ.routine;

import PlanQ.PlanQ.Member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "is_clear")
    private boolean isClear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
