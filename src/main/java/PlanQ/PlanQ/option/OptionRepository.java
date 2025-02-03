package PlanQ.PlanQ.option;

import PlanQ.PlanQ.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findAllByQuestion(Question question);
}
