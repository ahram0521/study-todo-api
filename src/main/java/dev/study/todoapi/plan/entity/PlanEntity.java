package dev.study.todoapi.plan.entity;

import dev.study.todoapi.common.BaseEntity;
import dev.study.todoapi.routine.entity.RoutineEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "plans")
public class PlanEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Comment("일정 고유 번호")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    @Comment("루틴 ID")
    private RoutineEntity routine;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    @Comment("일정 내용")
    private String content;

    @Column(name = "plan_date", nullable = false)
    @Comment("일정 적용일")
    private LocalDate planDate;

    @Column(name = "completed_date", nullable = true)
    @Comment("일정 완료일")
    private LocalDate completedDate;

    @Column(name = "is_completed", columnDefinition = "TINYINT", nullable = true)
    @Comment("완료 여부")
    private Integer isCompleted;
}
