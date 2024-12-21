package PlanQ.PlanQ.todo;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.Member.MemberService;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.security.oauth2.CustomOAuth2UserService;
import PlanQ.PlanQ.todo.dto.request.RequestTodoDto;
import PlanQ.PlanQ.todo.dto.response.ResponseTodoDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberService memberService;

    public Todo findById(Long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 Todo Entity 찾지 못함: " + id));
    }

    public List<ResponseTodoDto> findAllbyMember(String accessToken){
        Member member = memberService.findByAccessToken(accessToken);
        return todoRepository.findAllByMember(member).stream()
                .map(Todo :: toResponseTotoDto)
                .toList();
    }

    @Transactional
    public Long createTodo(String accessToken,RequestTodoDto requestTodoDto){
        Member member = memberService.findByAccessToken(accessToken);
        Color color = Color.valueOf(requestTodoDto.getColor());
        Todo todo = requestTodoDto.toEntity(member, color);
        todoRepository.save(todo);
        return todo.getId();
    }

    @Transactional
    public ResponseTodoDto editTodo(String accessToken, Long todoId, RequestTodoDto requestTodoDto){
        Todo todo = findById(todoId);
        Color color = Color.valueOf(requestTodoDto.getColor());
        todo.edit(requestTodoDto, color);
        todoRepository.save(todo);
        return todo.toResponseTotoDto();
    }

    @Transactional
    public boolean deleteTodo(String accessToken, Long todoId){
        Todo todo = findById(todoId);
        todoRepository.delete(todo);
        return true;
    }

    @Transactional
    public void updateIsClear(String accessToken, Todo todo){
        todo.updateIsClear();
        todoRepository.save(todo);
    }
}
