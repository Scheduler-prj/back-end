package PlanQ.PlanQ.config;

import PlanQ.PlanQ.notification.NotificationService;
import PlanQ.PlanQ.routine.RoutineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfig {
    private final RoutineService routineService;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 0 0 * * *")
    public void printDate () {
        routineService.resetRoutine();
        log.info("루틴 초기화");
    }

    @Scheduled(cron = " 0 0 18 * * *") // 테스트용 0 * * * * *  실제 0 0 18 * * *
    @Transactional
    public void bringUnclearSchedule () {
        notificationService.bringUnclearDateNotification();
        log.info("알림");
    }
}
