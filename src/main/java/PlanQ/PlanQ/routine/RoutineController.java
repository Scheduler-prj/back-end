package PlanQ.PlanQ.routine;

import PlanQ.PlanQ.routine.dto.request.RequestRoutineDto;
import PlanQ.PlanQ.routine.dto.response.ResponseRoutineDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routine")
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    @Operation(summary = "루틴 확인", description = "루틴 확인")
    @GetMapping()
    public ResponseEntity<List<ResponseRoutineDto>> viewAllRoutine(){
        return ResponseEntity.ok(routineService.findAllRoutine());
    }

    @Operation(summary = "루틴 생성", description = "루틴 생성")
    @PostMapping()
    public ResponseEntity<Long> createRoutine(@RequestBody @Validated RequestRoutineDto requestRoutineDto){
        return ResponseEntity.ok(routineService.createRoutine(requestRoutineDto));
    }

    @Operation(summary = "루틴 수정", description = "루틴 수정")
    @PutMapping("/{routine_id}")
    public ResponseEntity<Long> editRoutine(@PathVariable Long routine_id, @RequestBody @Validated RequestRoutineDto requestRoutineDto){
        Long response = routineService.editRoutine(routine_id, requestRoutineDto);
        if(response != 0L){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "루틴 삭제", description = "루틴 삭제")
    @DeleteMapping("/{routine_id}")
    public ResponseEntity<Boolean> deleteRoutine(@PathVariable Long routine_id){
        if(routineService.deleteRoutine(routine_id)){
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(false);
        }
    }

    @Operation(summary = "루틴 완료", description = "루틴 완료")
    @PutMapping("/clear/{routine_id}")
    public ResponseEntity<Long> clearRoutine(@PathVariable Long routine_id){
        Long response = routineService.clearRoutine(routine_id);
        if(response != 0L){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }
}
