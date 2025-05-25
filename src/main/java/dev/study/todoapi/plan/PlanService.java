package dev.study.todoapi.plan;

import dev.study.todoapi.plan.dto.PlanRequestDto;
import dev.study.todoapi.plan.entity.PlanEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Long createPlan(PlanRequestDto planRequestDto) {
        PlanEntity savePlan = new PlanEntity().insertEntity(planRequestDto);

        PlanEntity saved = planRepository.save(savePlan);

        return saved.getId();
    }

    @Transactional
    public void updatePlan(Long id, PlanRequestDto planRequestDto) {
        PlanEntity nowPlan = planRepository.findById(id).orElseThrow();

        nowPlan.updateEntity(planRequestDto);
    }

    @Transactional
    public void setPlanCompleted(Long id, Integer completed) {
        PlanEntity nowPlan = planRepository.findById(id).orElseThrow();

        nowPlan.updateCompleted(completed);
    }

    @Transactional
    public void deletePlan(Long id) {
        PlanEntity nowPlan = planRepository.findById(id).orElseThrow();

        nowPlan.deleteEntity();
    }
}
