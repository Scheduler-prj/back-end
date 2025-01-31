package PlanQ.PlanQ.todo;

import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.Member.MemberService;
import PlanQ.PlanQ.global.Color;
import PlanQ.PlanQ.notification.Notification;
import PlanQ.PlanQ.security.oauth2.CustomOAuth2UserService;
import PlanQ.PlanQ.todo.dto.request.RequestTodoDto;
import PlanQ.PlanQ.todo.dto.response.ResponseTodoDto;
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
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberService memberService;

    public Todo findById(Long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 Todo Entity 찾지 못함: " + id));
    }

    public List<ResponseTodoDto> findAllbyMemberAndYearMonth(Long year, Long month){
        Member member = memberService.getMember();
        return todoRepository.findAllByMemberAndYearMonth(member, year,month).stream()
                .map(Todo :: toResponseTotoDto)
                .toList();
    }

    @Transactional
    public Long createTodo(RequestTodoDto requestTodoDto){
        Member member = memberService.getMember();
        Todo todo = requestTodoDto.toEntity(member);
        todoRepository.save(todo);
        return todo.getId();
    }

    @Transactional
    public ResponseTodoDto editTodo(Long todoId, RequestTodoDto requestTodoDto){
        Todo todo = findById(todoId);
        todo.edit(requestTodoDto);
        todoRepository.save(todo);
        return todo.toResponseTotoDto();
    }

    @Transactional
    public boolean deleteTodo(Long todoId){
        Todo todo = findById(todoId);
        todoRepository.delete(todo);
        return true;
    }

    @Transactional
    public void updateIsClear(Todo todo){
        todo.updateIsClear();
    }

    public List<Notification> bringUnclearData(){
        return todoRepository.findAllByCalender_IsClearFalseAndCalender_AlarmTrueAndTodoAtBetween(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        , LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0).minusNanos(1)).stream()
                .map(Todo :: toNotification)
                .toList();
    }
}
