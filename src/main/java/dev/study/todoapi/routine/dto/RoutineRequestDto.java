package dev.study.todoapi.routine.dto;

import dev.study.todoapi.routine.entity.RoutineType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class RoutineRequestDto {
    @Schema(description = "루틴 시작일", example = "2025-05-10")
    private LocalDate startDate;

    @Schema(description = "루틴 종료일", example = "2025-05-15")
    private LocalDate endDate;

    @Schema(description = "루틴 타입 (daily: 매일, weekly: 주별, monthly: 월별)", example = "daily")
    private RoutineType repeatType;

    @Schema(description = "루틴 조건 (daily: null, weekly: 요일, monthly: 특정날짜)", example = " ")
    private String repeatValue;

    @Schema(description = "루틴 내용", example = "매일 회사가기ㅠ")
    private String content;
}
