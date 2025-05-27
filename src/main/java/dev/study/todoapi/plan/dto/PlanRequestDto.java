package dev.study.todoapi.plan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class PlanRequestDto {
    @Schema(description = "일정 내용", example = "학교가기")
    private String content;

    @Schema(description = "일정 적용일", example = "2025-05-26")
    private LocalDate planDate;

    @Schema(description = "일정 완료 여부(0: 미완료, 1: 완료)", example = "0")
    private Integer isCompleted = 0;
}
