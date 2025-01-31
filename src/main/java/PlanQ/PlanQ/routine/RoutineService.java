package PlanQ.PlanQ.routine;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.Member.MemberService;
import PlanQ.PlanQ.notification.Notification;
import PlanQ.PlanQ.routine.dto.request.RequestRoutineDto;
import PlanQ.PlanQ.routine.dto.response.ResponseRoutineDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final MemberService memberService;

    public Routine findById(Long routineId){
        return routineRepository.findById(routineId)
                .orElseThrow(() -> new EntityNotFoundException("해당 루틴 Id 찾지 못험" + routineId));
    }

    public List<ResponseRoutineDto> findAllRoutine(){
        Member member = memberService.getMember();
        return routineRepository.findAllByMember(member).stream()
                .map(Routine :: toResponseRoutineDto)
                .toList();
    }

    @Transactional
    public Long createRoutine(RequestRoutineDto requestRoutineDto){
        Member member = memberService.getMember();
        Routine routine = requestRoutineDto.toEntity(member);
        routineRepository.save(routine);
        return routine.getId();
    }

    @Transactional
    public Long editRoutine(Long routineId, RequestRoutineDto requestRoutineDto){
        Routine routine = findById(routineId);
        Member member = memberService.getMember();
        if(!routine.getMember().equals(member)){
            log.error("권한 없음");
            return 0L;
        }
        routine.edit(requestRoutineDto);
        return routineId;
    }

    @Transactional
    public boolean deleteRoutine(Long routineId){
        Routine routine = findById(routineId);
        Member member = memberService.getMember();
        if(!routine.getMember().equals(member)){
            log.error("권한 없음");
            return false;
        }
        routineRepository.delete(routine);
        return true;
    }

    @Transactional
    public Long clearRoutine(Long routineId){
        Routine routine = findById(routineId);
        Member member = memberService.getMember();
        if(!routine.getMember().equals(member)){
            log.error("권한 없음");
            return 0L;
        }
        routine.changeClear();
        return routineId;
    }

    @Transactional
    public void resetRoutine(){
        routineRepository.findAll().forEach(Routine::reset);
    }

    public List<Notification> bringUnclearData()
    {
        Dotw dotw = Dotw.fromString(LocalDateTime.now().getDayOfWeek().name());
        return routineRepository.findAllByCalenderFalse(dotw).stream()
                .map(Routine :: toNotification)
                .toList();
    }
}
