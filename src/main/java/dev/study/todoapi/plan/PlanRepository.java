package dev.study.todoapi.plan;

import dev.study.todoapi.plan.entity.PlanEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {

    List<PlanEntity> findByPlanDateAndIsDeleted(LocalDate PlanDate, Integer IsDeleted);
}
