package PlanQ.PlanQ.notification;


import PlanQ.PlanQ.Member.Member;
import PlanQ.PlanQ.Member.MemberService;
import PlanQ.PlanQ.plan.Plan;
import PlanQ.PlanQ.plan.PlanService;
import PlanQ.PlanQ.routine.Routine;
import PlanQ.PlanQ.routine.RoutineService;
import PlanQ.PlanQ.todo.Todo;
import PlanQ.PlanQ.todo.TodoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final TodoService todoService;
    private final PlanService planService;
    private final RoutineService routineService;
    private final MemberService memberService;

    private final SseEmitterRepository sseEmitterRepository;

    private static final Long TIMEOUT = 30 * 60 * 1000L;

    public Notification findById(Long id){
        return notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 entity 존재하지 않음"));
    }

    public void bringUnclearDateNotification(){
        List<Notification> notificationList = new ArrayList<>();
        notificationList.addAll(bringUncheckPlan());
        notificationList.addAll(bringUncheckRoutine());
        notificationList.addAll(bringUncheckTodo());
        System.out.println(notificationList);
        System.out.println("알림 개수: "+notificationList.size());
        notificationRepository.saveAll(notificationList);
        sendNotification(notificationList);
    }

    public List<Notification> bringUncheckTodo(){
        return todoService.bringUnclearData();
    }

    public List<Notification> bringUncheckRoutine(){
        return routineService.bringUnclearData();
    }

    public List<Notification> bringUncheckPlan(){
        return planService.bringUnclearData();
    }

    @Transactional
    public ResponseNotificationDto findByNotification(Long notificationId){
        Notification notification = findById(notificationId);
        Member member = memberService.getMember();

        if(notification.getType().equals(Type.PLAN) && notification.getPlan().getMember().equals(member)){
            notification.readIt();
            return notification.toResponse();
        }
        if(notification.getType().equals(Type.TODO) && notification.getTodo().getMember().equals(member)){
            notification.readIt();
            return notification.toResponse();
        }
        if(notification.getType().equals(Type.ROUTINE) && notification.getRoutine().getMember().equals(member)){
            notification.readIt();
            return notification.toResponse();
        }

        log.error("권한이 없는 알림 읽기입니다.");

        return null;
    }

    public SseEmitter subscribe(String lastEventId) {
        Member member = memberService.getMember();
        String emitterId = member.getId().toString() + "_" + System.currentTimeMillis();
        SseEmitter emitter = sseEmitterRepository.save(emitterId, new SseEmitter(TIMEOUT));

        emitter.onCompletion(() -> sseEmitterRepository.deleteEmitterById(emitterId));
        emitter.onTimeout(() -> sseEmitterRepository.deleteEmitterById(emitterId));
        emitter.onError(e -> sseEmitterRepository.deleteEmitterById(emitterId));

        sendEvent(emitter, emitterId, "연결되었습니다.");

        if (!lastEventId.isEmpty()) {
            Map<String, Object> eventCache = sseEmitterRepository.findAllEventCacheStartsWith(member.getId().toString());
            eventCache.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendEvent(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }


    public void sendNotification(List<Notification> notificationList) {
        for(Notification notification : notificationList){
            String memberId = Objects.requireNonNull(getMemberIdFromNotification(notification)).toString();

            Map<String, SseEmitter> emitters = sseEmitterRepository.findAllEmittersByIdPrefix(memberId);
            emitters.forEach((key, emitter) -> {
                try {
                    sendEvent(emitter, key, notification.toResponse());
                } catch (Exception e) {
                    sseEmitterRepository.deleteEmitterById(key);
                }
            });
        }
    }

    private Long getMemberIdFromNotification(Notification notification) {
        if (notification.getType() == Type.PLAN && notification.getPlan() != null) {
            return notification.getPlan().getMember().getId();
        } else if (notification.getType() == Type.ROUTINE && notification.getRoutine() != null) {
            return notification.getRoutine().getMember().getId();
        } else if (notification.getType() == Type.TODO && notification.getTodo() != null) {
            return notification.getTodo().getMember().getId();
        }
        return null;
    }

    private void sendEvent(SseEmitter emitter, String eventId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("notification")
                    .data(data, MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            sseEmitterRepository.deleteEmitterById(eventId);
            emitter.completeWithError(e);
        }
    }


}
