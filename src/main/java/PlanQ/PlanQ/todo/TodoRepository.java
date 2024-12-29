package PlanQ.PlanQ.todo;


import PlanQ.PlanQ.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByMember(Member member);
}