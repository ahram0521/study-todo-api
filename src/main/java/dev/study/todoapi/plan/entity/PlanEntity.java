package dev.study.todoapi.plan.entity;

import dev.study.todoapi.common.BaseEntity;
import dev.study.todoapi.plan.dto.PlanRequestDto;
import dev.study.todoapi.routine.entity.RoutineEntity;
import dev.study.todoapi.routine.entity.RoutineType;
import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "plans")
@NoArgsConstructor
public class PlanEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Comment("일정 고유 번호")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    @Comment("루틴 ID")
    private RoutineEntity routineId;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    @Comment("일정 내용")
    private String content;

    @Column(name = "plan_date", nullable = false)
    @Comment("일정 적용일")
    private LocalDate planDate;

    @Column(name = "completed_date", nullable = true)
    @Comment("일정 완료일")
    private LocalDate completedDate;

    @Column(name = "is_completed", columnDefinition = "TINYINT DEFAULT 0", nullable = true)
    @Comment("완료 여부")
    private Integer isCompleted;

    @Column(name = "is_deleted", columnDefinition = "TINYINT DEFAULT 0")
    @Comment("삭제 여부")
    private Integer isDeleted;

    @Builder
    public PlanEntity(String content, LocalDate planDate, Integer isCompleted, RoutineEntity routineId) {
        this.content = content;
        this.planDate = planDate;
        this.isCompleted = isCompleted;
        this.routineId = routineId;
        this.isDeleted = 0;
    }

    public PlanEntity insertEntity(PlanRequestDto dto) {
        return PlanEntity.builder()
                .content(dto.getContent())
                .planDate(dto.getPlanDate())
                .isCompleted(dto.getIsCompleted())
                .build();
    }

    public void updateEntity(PlanRequestDto dto) {
        this.content = dto.getContent();
        this.planDate = dto.getPlanDate();
    }

    public void deleteEntity() {
        this.isDeleted = 1;
    }

    public void updateCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
        this.completedDate = LocalDate.now();
    }

    public static List<PlanEntity> insertFromRoutine(RoutineEntity routineEntity) {
        List<PlanEntity> plans = new ArrayList<>();
        LocalDate current = routineEntity.getStartDate();
        LocalDate end = routineEntity.getEndDate();
        String repeatValue = routineEntity.getRepeatValue();

        for (LocalDate date = current; !date.isAfter(end); date = date.plusDays(1)) {
            boolean insert = false;
            RoutineType type = routineEntity.getRepeatType();

            if (type == RoutineType.daily) {
                insert = true;
            } else if (type == RoutineType.weekly) {
                DayOfWeek weekday = date.getDayOfWeek();
                if (weekday == DayOfWeek.of(Integer.parseInt(repeatValue))) {
                    insert = true;
                }
            } else if (type == RoutineType.monthly) {
                if (Integer.toString(date.getDayOfMonth()).equals(repeatValue)) {
                    insert = true;
                }
            }

            if (insert) {
                PlanEntity plan = PlanEntity.builder()
                        .content(routineEntity.getContent())
                        .planDate(date)
                        .isCompleted(0)
                        .routineId(routineEntity)
                        .build();

                plans.add(plan);
            }
        }

        return plans;
    }
}
