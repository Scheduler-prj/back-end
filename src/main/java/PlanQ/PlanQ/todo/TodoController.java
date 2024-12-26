package PlanQ.PlanQ.todo;

import PlanQ.PlanQ.todo.dto.request.RequestTodoDto;
import PlanQ.PlanQ.todo.dto.response.ResponseTodoDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "투두 조회", description = "달 투두 조회")
    @GetMapping("/{month}")
    public ResponseEntity<List<ResponseTodoDto>> viewTodoAll(@RequestHeader(value = "Authorization") final String accessToken){
        return ResponseEntity.ok(todoService.findAllbyMember(accessToken));
    }

    @Operation(summary = "투두 생성", description = "투두 생성")
    @PostMapping()
    public ResponseEntity<Long> createTodo(@RequestHeader(value = "Authorization") final String accessToken, @RequestBody @Validated RequestTodoDto requestTodoDto){
        Long response = todoService.createTodo(accessToken, requestTodoDto);
        if(response == null)
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "투두 수정", description = "투두 수정")
    @PutMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> editTodo(@RequestHeader(value = "Authorization") final String accessToken ,@PathVariable Long todoId, @RequestBody @Validated RequestTodoDto requestTodoDto){
        ResponseTodoDto response = todoService.editTodo(accessToken, todoId, requestTodoDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "투두 삭제", description = "투두 삭제")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Boolean> deleteTodo(@RequestHeader(value = "Authorization") final String accessToken,@PathVariable Long todoId){
        boolean b = todoService.deleteTodo(accessToken, todoId);
        if(b)
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.ok(false);
    }

}