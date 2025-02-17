package PlanQ.PlanQ.plan;

import PlanQ.PlanQ.plan.dto.request.RequestPlanDto;
import PlanQ.PlanQ.plan.dto.response.ResponsePlanDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    @Operation(summary = "일정 보기", description = "일정 보기")
    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<ResponsePlanDto>> viewMonthPlan(@PathVariable("year") Long year,
                                                               @PathVariable("month") Long month){
        List<ResponsePlanDto> responsePlanDtoList = planService.viewAllByMemberAndYearMonth(year, month);
        if(responsePlanDtoList.isEmpty()){
            return ResponseEntity.badRequest().body(responsePlanDtoList);
        }
        return ResponseEntity.ok(responsePlanDtoList);
    }
    @Operation(summary = "일정 생성", description = "일정 생성")
    @PostMapping()
    public ResponseEntity<Boolean> createPlan(@RequestBody @Validated RequestPlanDto requestPlanDto){
        return ResponseEntity.ok(planService.createPlan(requestPlanDto));
    }
    @Operation(summary = "일정 수정", description = "일정 수정")
    @PutMapping("/{planId}")
    public ResponseEntity<Boolean> editPlan(@PathVariable Long planId,
                                            @RequestBody @Validated RequestPlanDto requestPlanDto){
        return ResponseEntity.ok(planService.editPlan(planId, requestPlanDto));
    }
    @Operation(summary = "일정 삭제", description = "일정 삭제")
    @DeleteMapping("/{planId}")
    public ResponseEntity<Boolean> deletePlan(@PathVariable Long planId){
        if(planService.deletePlan(planId))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.badRequest().body(false);
    }
    @Operation(summary = "일정 클리어", description = "일정 클리어")
    @PutMapping("/clear/{planId}")
    public ResponseEntity<Boolean> clearPlan(@PathVariable Long planId){
        if(planService.clearPlan(planId))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.badRequest().body(false);
    }
}
