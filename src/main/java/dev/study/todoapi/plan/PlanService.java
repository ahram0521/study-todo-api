package dev.study.todoapi.plan;

import dev.study.todoapi.plan.dto.PlanRequestDto;
import dev.study.todoapi.plan.entity.PlanEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Long createPlan(PlanRequestDto planRequestDto) {
        PlanEntity savePlan = PlanEntity.builder()
                .content(planRequestDto.getContent())
                .planDate(planRequestDto.getPlanDate())
                .build();

        PlanEntity saved = planRepository.save(savePlan);

        return saved.getId();
    }
}
