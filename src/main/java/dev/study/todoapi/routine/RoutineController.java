package dev.study.todoapi.routine;

import dev.study.todoapi.routine.dto.RoutineRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Routine API", description = "루틴 일정 관련 API")
@Controller
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    @Operation(summary = "루틴 일정 생성", description = "새로운 루틴 일정 생성")
    @PostMapping
    public ResponseEntity<Long> createRoutine(@RequestBody RoutineRequestDto routineRequestDto) {
        Long routineId = routineService.createRoutine(routineRequestDto);
        return ResponseEntity.ok(routineId);
    }

    @Operation(summary = "루틴 일정 수정", description = "특정 루틴 일정 수정")
    @PutMapping("/{routine-id}")
    public ResponseEntity<Void> updateRoutine(@PathVariable("routine-id") Long routineId, @RequestBody RoutineRequestDto routineRequestDto) {
        routineService.updateRoutine(routineId, routineRequestDto);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "루틴 일정 삭제", description = "특정 루틴 일정 삭제")
    @DeleteMapping("/{routine-id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable("routine-id") Long routineId) {
        routineService.deleteRoutine(routineId);

        return ResponseEntity.noContent().build();
    }
}
