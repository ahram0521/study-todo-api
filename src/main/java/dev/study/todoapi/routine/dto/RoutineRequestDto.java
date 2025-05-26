package dev.study.todoapi.routine.dto;

import dev.study.todoapi.routine.entity.RoutineType;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class RoutineRequestDto {
    private LocalDate startDate;

    private LocalDate endDate;

    private RoutineType repeatType;

    private String repeatValue;

    private String content;
}
