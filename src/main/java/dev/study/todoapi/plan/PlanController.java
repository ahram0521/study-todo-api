package dev.study.todoapi.plan;

import dev.study.todoapi.plan.dto.PlanRequestDto;
import dev.study.todoapi.plan.entity.PlanEntity;
import dev.study.todoapi.routine.dto.PlanDailyCountDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Plan API", description = "일정 관련 API")
@Controller
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @Operation(summary = "일정 생성", description = "새로운 일정 생성")
    @PostMapping
    public ResponseEntity<Long> createPlan(@RequestBody PlanRequestDto planRequestDto) {
        Long planId = planService.createPlan(planRequestDto);
        return ResponseEntity.ok(planId);
    }

    @Operation(summary = "일정 수정", description = "특정 일정 수정")
    @PatchMapping("/{plan-id}")
    public ResponseEntity<Void> updatePlan(@PathVariable("plan-id") Long planId, @RequestBody PlanRequestDto planRequestDto) {
        planService.updatePlan(planId, planRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "일정 완료/취소", description = "일정 완료/취소 처리")
    @PatchMapping("/{plan-id}/completed")
    public ResponseEntity<Void> setPlanCompleted(@PathVariable("plan-id") Long planId, @RequestBody PlanRequestDto planRequestDto) {
        planService.setPlanCompleted(planId, planRequestDto.getIsCompleted());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "일정 삭제", description = "특정 일정 삭제")
    @DeleteMapping("/{plan-id}")
    public ResponseEntity<Void> deletePlan(@PathVariable("plan-id") Long planId) {
        planService.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "특정 날짜의 일정 조회", description = "특정 날짜의 일정 조회")
    @GetMapping
    public ResponseEntity<List<PlanEntity>> getPlanByPlanDate(@RequestParam("date") String planDate) {
        List<PlanEntity> dailyPlan = planService.getPlansByDate(planDate);

        return ResponseEntity.ok(dailyPlan);
    }

    @GetMapping("/count/{month}")
    public ResponseEntity<List<PlanDailyCountDto>> getDailyPlanCount(@PathVariable("month") String month) {
        System.out.println("요청 month 파라미터 = " + month);

        LocalDate dateMonth = LocalDate.parse(month + "-01");
        List<PlanDailyCountDto> dailyPlan = planService.getCountDailyPlan(dateMonth);

        return ResponseEntity.ok(dailyPlan);
    }
}
