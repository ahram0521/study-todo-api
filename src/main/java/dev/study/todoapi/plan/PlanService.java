package dev.study.todoapi.plan;

import dev.study.todoapi.common.exception.NotFoundException;
import dev.study.todoapi.plan.dto.PlanRequestDto;
import dev.study.todoapi.plan.entity.PlanEntity;
import dev.study.todoapi.routine.dto.PlanDailyCountDto;
import java.time.LocalDate;
import java.util.List;
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
        PlanEntity nowPlan = planRepository.findById(id).orElseThrow(() -> new NotFoundException("일정을 찾을 수 없습니다."));

        nowPlan.updateEntity(planRequestDto);
    }

    @Transactional
    public void setPlanCompleted(Long id, Integer completed) {
        PlanEntity nowPlan = planRepository.findById(id).orElseThrow(() -> new NotFoundException("일정을 찾을 수 없습니다."));

        nowPlan.updateCompleted(completed);
    }

    @Transactional
    public void deletePlan(Long id) {
        PlanEntity nowPlan = planRepository.findById(id).orElseThrow(() -> new NotFoundException("일정을 찾을 수 없습니다."));

        nowPlan.deleteEntity();
    }

    public List<PlanEntity> getPlansByDate(String planDate) {
        LocalDate date = LocalDate.parse(planDate);

        return planRepository.findByPlanDateAndIsDeleted(date, 0);
    }

    public List<PlanDailyCountDto> getCountDailyPlan(LocalDate month) {
        LocalDate startDate = month.withDayOfMonth(1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return planRepository.getCountDailyPlan(startDate, endDate);
    }
}
