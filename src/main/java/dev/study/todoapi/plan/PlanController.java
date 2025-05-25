package dev.study.todoapi.plan;

import dev.study.todoapi.plan.dto.PlanRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping()
    public ResponseEntity<Long> createPlans(@RequestBody PlanRequestDto planRequestDto) {
        Long planId = planService.createPlan(planRequestDto);
        return ResponseEntity.ok(planId);
    }
}
