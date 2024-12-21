package PlanQ.PlanQ.plan;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.Member.MemberService;
import PlanQ.PlanQ.plan.dto.request.RequestPlanDto;
import PlanQ.PlanQ.plan.dto.response.ResponsePlanDto;
import PlanQ.PlanQ.report.dto.request.RequestReportDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final MemberService memberService;

    public Plan findById(Long id){
        return planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 Plan Entity 찾지 못함: " + id));
    }
    public List<ResponsePlanDto> viewAllByMemberAndMonth(String accessToken, Long month){
        Member member = memberService.findByAccessToken(accessToken);
        return planRepository.findAllByMemberAndMonth(member, month).stream()
                .map(Plan :: toResponsePlanDto)
                .toList();
    }

    @Transactional
    public boolean createPlan(String accessToken, RequestPlanDto requestPlanDto){
        Member member = memberService.findByAccessToken(accessToken);
        Plan plan = requestPlanDto.toEntity(member);
        planRepository.save(plan);
        return true;
    }

    @Transactional
    public boolean editPlan(String accessToken, Long planId, RequestPlanDto requestPlanDto){
        Member member = memberService.findByAccessToken(accessToken);
        Plan plan = findById(planId);
        if(!member.equals(plan.getMember())){
            log.error("수정 불가");
            return false;
        }
        plan.update(requestPlanDto);
        return true;
    }

    @Transactional
    public boolean deletePlan(String accessToken, Long planId){
        Member member = memberService.findByAccessToken(accessToken);
        Plan plan = findById(planId);
        if(!member.equals(plan.getMember())){
            log.error("삭제 불가");
            return false;
        }
        planRepository.delete(plan);
        return true;
    }

    @Transactional
    public boolean clearPlan(String accessToken, Long planId) {
        Member member = memberService.findByAccessToken(accessToken);
        Plan plan = findById(planId);
        if(!member.equals(plan.getMember())){
            log.error("클리어 불가");
            return false;
        }
        plan.clear();
        return true;
    }

}
