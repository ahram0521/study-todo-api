package dev.study.todoapi.routine.entity;

import dev.study.todoapi.common.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "routines")
public class RoutineEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Comment("루틴 고유 번호")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "routine_type", nullable = false)
    @Comment("루틴 타입 (daily:매일, weekly:주간, monthly:월간)")
    private RoutineType routineType;

    @Column(name = "repeat_value", nullable = true)
    @Comment("루틴 조건 (daily: null, weekly: 요일, monthly: 특정일)")
    private String repeatValue;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    @Comment("루틴 내용")
    private String content;

    @Column(name = "start_date", nullable = false)
    @Comment("루틴 시작일")
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @Comment("루틴 종료일")
    private LocalDate endDate;
}
