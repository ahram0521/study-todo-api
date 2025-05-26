package dev.study.todoapi.routine.entity;

import dev.study.todoapi.common.BaseEntity;
import dev.study.todoapi.routine.dto.RoutineRequestDto;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "routines")
@NoArgsConstructor
public class RoutineEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Comment("루틴 고유 번호")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "routine_type", nullable = false)
    @Comment("루틴 타입 (daily:매일, weekly:주간, monthly:월간)")
    private RoutineType repeatType;

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

    @Column(name = "is_deleted", columnDefinition = "TINYINT DEFAULT 0")
    @Comment("삭제 여부")
    private Integer isDeleted;

    @Builder
    public RoutineEntity(LocalDate startDate, LocalDate endDate, RoutineType repeatType, String repeatValue, String content) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.repeatType = repeatType;
        this.repeatValue = repeatValue;
        this.content = content;
        this.isDeleted = 0;
    }

    public RoutineEntity insertEntity(RoutineRequestDto dto) {
        return RoutineEntity.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .repeatType(dto.getRepeatType())
                .repeatValue(dto.getRepeatValue())
                .content(dto.getContent())
                .build();
    }

    public void updateEntity(RoutineRequestDto dto) {
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.repeatType = dto.getRepeatType();
        this.repeatValue = dto.getRepeatValue();
        this.content = dto.getContent();
    }

    public void deleteEntity() {
        this.isDeleted = 1;
    }
}
