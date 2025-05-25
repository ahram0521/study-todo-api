package dev.study.todoapi.plan.dto;

import java.time.LocalDate;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class PlanRequestDto {
    @NotNull
    private String content;

    @NotNull
    private LocalDate planDate;
}
