package PlanQ.PlanQ.routine;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.Member.MemberService;
import PlanQ.PlanQ.routine.dto.request.RequestRoutineDto;
import PlanQ.PlanQ.routine.dto.response.ResponseRoutineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final MemberService memberService;

   /* public List<ResponseRoutineDto> findAllRoutine(){
        Member member = null;
        return routineRepository.findAllByMember(member).stream()
                .map(Routine :: toResponseRoutineDto)
                .toList();
    }*/

   /* @Transactional
    public Long createRoutine(RequestRoutineDto requestRoutineDto){
        Member member = new Member(null, null, null, null);
        Routine routine = requestRoutineDto.toEntity(member);
        routineRepository.save(routine);
        return routine.getId();
    }*/
}
