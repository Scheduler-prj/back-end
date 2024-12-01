package PlanQ.PlanQ.todo;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.global.Color;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Enumerated(EnumType.STRING)
    private Color color;

    private boolean alarm;

    private String comment;

    @Column(name = "is_clear")
    private boolean isClear;
}
