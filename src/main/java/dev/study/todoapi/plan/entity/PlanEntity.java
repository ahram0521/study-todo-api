package dev.study.todoapi.plan.entity;

import dev.study.todoapi.common.BaseEntity;
import dev.study.todoapi.plan.dto.PlanRequestDto;
import dev.study.todoapi.routine.entity.RoutineEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
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

    @Builder
    public PlanEntity(String content, LocalDate planDate, Integer isCompleted) {
        this.content = content;
        this.planDate = planDate;
        this.isCompleted = isCompleted;
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
}
