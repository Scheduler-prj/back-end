package PlanQ.PlanQ.embeddad;

import PlanQ.PlanQ.global.Color;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calender {

    @NotBlank
    private String title;

    @NotBlank
    private String comment;

    @Enumerated(EnumType.STRING)
    private Color color;

    @NotNull
    private boolean alarm;

    @Column(name = "is_clear")
    private boolean isClear;

    public void checkClear(){
        this.isClear = !isClear;
    }

    public void changeClear(){
        this.isClear = true;
    }
}
