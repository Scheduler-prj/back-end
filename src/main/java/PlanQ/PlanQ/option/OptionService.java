package PlanQ.PlanQ.option;

import PlanQ.PlanQ.option.dto.response.ResponseOptionDto;
import PlanQ.PlanQ.question.Question;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    public Option findById(Long id){
        return optionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 Option 찾지 못함"));
    }

    public List<ResponseOptionDto> findAllOptions(Question question){
        return optionRepository.findAllByQuestion(question).stream()
                .map(Option :: toResponseOptionDto)
                .toList();
    }
}
