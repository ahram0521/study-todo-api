package dev.study.todoapi.routine;

import dev.study.todoapi.common.exception.NotFoundException;
import dev.study.todoapi.plan.PlanRepository;
import dev.study.todoapi.plan.entity.PlanEntity;
import dev.study.todoapi.routine.dto.RoutineRequestDto;
import dev.study.todoapi.routine.entity.RoutineEntity;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final PlanRepository planRepository;

    public Long createRoutine(RoutineRequestDto routineRequestDto) {
        RoutineEntity routine = new RoutineEntity().insertEntity(routineRequestDto);

        RoutineEntity saveRoutine = routineRepository.save(routine);

        List<PlanEntity> plans = PlanEntity.insertFromRoutine(saveRoutine);
        planRepository.saveAll(plans);

        return saveRoutine.getId();
    }

    @Transactional
    public void updateRoutine(Long id, RoutineRequestDto routineRequestDto) {
        RoutineEntity nowRoutine = routineRepository.findById(id).orElseThrow(() -> new NotFoundException("루틴을 찾을 수 없습니다."));

        nowRoutine.updateEntity(routineRequestDto);

        List<PlanEntity> routinePlans = planRepository.findDeletedRoutineTarget(nowRoutine, 0, LocalDate.now());

        // delete
        for (PlanEntity plan : routinePlans) {
            plan.deleteEntity();
        }

        List<PlanEntity> newPlans = PlanEntity.insertFromRoutine(nowRoutine);
        planRepository.saveAll(newPlans);
    }

    @Transactional
    public void deleteRoutine(Long id) {
        RoutineEntity nowRoutine = routineRepository.findById(id).orElseThrow(() -> new NotFoundException("루틴을 찾을 수 없습니다."));

        nowRoutine.deleteEntity();

        List<PlanEntity> routinePlans = planRepository.findDeletedRoutineTarget(nowRoutine, 0, LocalDate.now());
        for (PlanEntity plan : routinePlans) {
            plan.deleteEntity();
        }
    }
}
