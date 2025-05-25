package dev.study.todoapi.plan.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class PlanRequestDto {
    private String content;

    private LocalDate planDate;

    private Integer isCompleted = 0;
}
