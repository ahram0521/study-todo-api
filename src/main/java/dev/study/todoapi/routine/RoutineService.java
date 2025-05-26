package dev.study.todoapi.routine;

import dev.study.todoapi.plan.PlanRepository;
import dev.study.todoapi.plan.entity.PlanEntity;
import dev.study.todoapi.routine.dto.RoutineRequestDto;
import dev.study.todoapi.routine.entity.RoutineEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
