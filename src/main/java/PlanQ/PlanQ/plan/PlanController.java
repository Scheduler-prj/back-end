package PlanQ.PlanQ.plan;

import PlanQ.PlanQ.plan.dto.request.RequestPlanDto;
import PlanQ.PlanQ.plan.dto.response.ResponsePlanDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    @Operation(summary = "일정 보기", description = "일정 보기")
    @GetMapping("/{month}")
    public ResponseEntity<List<ResponsePlanDto>> viewMonthPlan(@RequestHeader(value = "Authorizatuin") final String accessToken,
                                                              @PathVariable Long month){
        List<ResponsePlanDto> responsePlanDtoList = planService.viewAllByMemberAndMonth(accessToken, month);
        if(responsePlanDtoList.isEmpty()){
            return ResponseEntity.badRequest().body(responsePlanDtoList);
        }
        return ResponseEntity.ok(responsePlanDtoList);
    }
    @Operation(summary = "일정 생성", description = "일정 생성")
    @PostMapping()
    public ResponseEntity<Boolean> createPlan(@RequestHeader(value = "Authorization") final String accessToken,
                                              @RequestBody RequestPlanDto requestPlanDto){
        return ResponseEntity.ok(planService.createPlan(accessToken, requestPlanDto));
    }
    @Operation(summary = "일정 수정", description = "일정 수정")
    @PutMapping("/{planId}")
    public ResponseEntity<Boolean> editPlan(@RequestHeader(value = "Authorization") final String accessToken,
                                            @PathVariable Long planId,
                                            @RequestBody RequestPlanDto requestPlanDto){
        return ResponseEntity.ok(planService.editPlan(accessToken, planId, requestPlanDto));
    }
    @Operation(summary = "일정 삭제", description = "일정 삭제")
    @DeleteMapping("/{planId}")
    public ResponseEntity<Boolean> deletePlan(@RequestHeader(value = "Authorization") final String accessToken,
                                              @PathVariable Long planId){
        if(planService.deletePlan(accessToken, planId))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.badRequest().body(false);
    }
    @Operation(summary = "일정 클리어", description = "일정 클리어")
    @PutMapping("/clear/{planId}")
    public ResponseEntity<Boolean> clearPlan(@RequestHeader(value = "Authorization") final String accessToken,
                                             @PathVariable Long planId){
        if(planService.clearPlan(accessToken, planId))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.badRequest().body(false);
    }
}
