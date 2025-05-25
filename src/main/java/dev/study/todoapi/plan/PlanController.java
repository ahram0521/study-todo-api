package dev.study.todoapi.plan;

import dev.study.todoapi.plan.dto.PlanRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping()
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
}
