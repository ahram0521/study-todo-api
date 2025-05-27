package dev.study.todoapi.routine.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class PlanDailyCountDto {
    private LocalDate planDate;
    private Long count;

    public PlanDailyCountDto(LocalDate planDate, Long count) {
        this.planDate = planDate;
        this.count = count;
    }
}
