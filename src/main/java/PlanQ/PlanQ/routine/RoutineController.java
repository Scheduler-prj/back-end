/*
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

 */
/* @PutMapping()
    public ResponseEntity<> editRoutine(){

    }

    @PutMapping()
    public ResponseEntity<> claerRoutine(){

    }*//*

}
*/
