package dev.study.todoapi.plan;

import dev.study.todoapi.plan.entity.PlanEntity;
import dev.study.todoapi.routine.entity.RoutineEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {

    List<PlanEntity> findByPlanDateAndIsDeleted(LocalDate PlanDate, Integer IsDeleted);

    @Query("""
        SELECT plan FROM PlanEntity plan
        WHERE plan.routineId = :routine
          AND plan.isDeleted = :isDeleted
          AND plan.planDate >= :today
    """)
    List<PlanEntity> findDeletedRoutineTarget(
        @Param("routine") RoutineEntity RoutineId,
        @Param("isDeleted") Integer IsDeleted,
        @Param("today") LocalDate PlanDate
    );
}
