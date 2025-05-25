package dev.study.todoapi.plan;

import dev.study.todoapi.plan.dto.PlanRequestDto;
import dev.study.todoapi.plan.entity.PlanEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

@Controller
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping
    public ResponseEntity<Long> createPlan(@RequestBody PlanRequestDto planRequestDto) {
        Long planId = planService.createPlan(planRequestDto);
        return ResponseEntity.ok(planId);
    }

    @PatchMapping("/{plan-id}")
    public ResponseEntity<Void> updatePlan(@PathVariable("plan-id") Long planId, @RequestBody PlanRequestDto planRequestDto) {
        planService.updatePlan(planId, planRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{plan-id}/completed")
    public ResponseEntity<Void> setPlanCompleted(@PathVariable("plan-id") Long planId, @RequestBody PlanRequestDto planRequestDto) {
        planService.setPlanCompleted(planId, planRequestDto.getIsCompleted());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{plan-id}")
    public ResponseEntity<Void> deletePlan(@PathVariable("plan-id") Long planId) {
        planService.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PlanEntity>> getPlanByPlanDate(@RequestParam("date") String planDate) {
        List<PlanEntity> dailyPlan = planService.getPlansByDate(planDate);

        return ResponseEntity.ok(dailyPlan);
    }
}
