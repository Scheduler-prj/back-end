package PlanQ.PlanQ.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNotificationDto {
    private Long id;

    private String status;

    private Type type;

    private boolean isRead;
}
