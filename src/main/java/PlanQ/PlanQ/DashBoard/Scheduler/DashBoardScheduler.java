package PlanQ.PlanQ.DashBoard.Scheduler;

import PlanQ.PlanQ.DashBoard.Service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DashBoardScheduler {
    private final DashBoardService dashBoardService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduler(){
        dashBoardService.getConsecutiveDays();
    }
}
