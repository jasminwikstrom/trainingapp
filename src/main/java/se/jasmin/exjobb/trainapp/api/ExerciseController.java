package se.jasmin.exjobb.trainapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jasmin.exjobb.trainapp.api.dto.CreateExerciseActivityDto;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewExerciseDto;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import se.jasmin.exjobb.trainapp.service.ExerciseActivityService;
import se.jasmin.exjobb.trainapp.service.ExerciseService;


@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseActivityService exerciseActivityService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Exercise createNewExercise(@RequestBody CreateNewExerciseDto createNewExerciseDto) {

        var savedMachine = exerciseService.createExercise(createNewExerciseDto);

        return ResponseEntity.ok(savedMachine).getBody();
    }


    @GetMapping
    public Exercise getAllMachine(@RequestParam(value = "name", required = false) String title) {

        Exercise exercise = (Exercise) exerciseService.getExercises(title);

        return exercise;
    }

    @PostMapping("/{id}/exerciseactivities")
    public ResponseEntity createNewExerciseActivity(
            @PathVariable(value = "id") String id,
            @RequestBody CreateExerciseActivityDto createExerciseActivityDto) {

        var optionalExerciseActivity = exerciseActivityService.createNewExerciseActivity(id, createExerciseActivityDto);

        if (optionalExerciseActivity.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(optionalExerciseActivity.get());
        }
    }
}
