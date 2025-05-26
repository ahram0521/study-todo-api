package dev.study.todoapi.routine;

import dev.study.todoapi.routine.dto.RoutineRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping
    public ResponseEntity<Long> createRoutine(@RequestBody RoutineRequestDto routineRequestDto) {
        Long routineId = routineService.createRoutine(routineRequestDto);
        return ResponseEntity.ok(routineId);
    }

    @PutMapping("/{routine-id}")
    public ResponseEntity<Void> updateRoutine(@PathVariable("routine-id") Long routineId, @RequestBody RoutineRequestDto routineRequestDto) {
        routineService.updateRoutine(routineId, routineRequestDto);

        return ResponseEntity.noContent().build();
    }
}
