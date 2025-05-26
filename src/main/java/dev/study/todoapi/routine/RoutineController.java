package dev.study.todoapi.routine;

import dev.study.todoapi.routine.dto.RoutineRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping
    public ResponseEntity<Long> createPlan(@RequestBody RoutineRequestDto routineRequestDto) {
        Long routineId = routineService.createRoutine(routineRequestDto);
        return ResponseEntity.ok(routineId);
    }
}
