package se.jasmin.exjobb.trainapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.jasmin.exjobb.trainapp.api.dto.AverageDto;
import se.jasmin.exjobb.trainapp.api.dto.CreateExerciseActivityDto;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewExerciseDto;
import se.jasmin.exjobb.trainapp.api.dto.ProgressDto;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import se.jasmin.exjobb.trainapp.service.AuthFacade;
import se.jasmin.exjobb.trainapp.service.ExerciseActivityService;
import se.jasmin.exjobb.trainapp.service.ExerciseService;
import se.jasmin.exjobb.trainapp.service.StatService;

import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseActivityService exerciseActivityService;

    @Autowired
    private StatService statService;

    @Autowired
    private AuthFacade authFacade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Exercise> createNewExercise(
            Authentication authentication,
            @RequestBody CreateNewExerciseDto createNewExerciseDto) {

        var loggedInUser = authFacade.getLoggedInUser(authentication.getName());

        var savedExercise = exerciseService.createExercise(loggedInUser, createNewExerciseDto);

        return ResponseEntity.ok(savedExercise).getBody();
    }


    @GetMapping
    public Exercise getAllExercises(@RequestParam(value = "name", required = false) String title) {

        Exercise exercise = (Exercise) exerciseService.getExercises(title);

        return exercise;
    }

    @PostMapping("/{id}/exerciseactivities")
    public ResponseEntity createNewExerciseActivity(
            Authentication authentication,
            @PathVariable(value = "id") String id,
            @RequestBody CreateExerciseActivityDto createExerciseActivityDto) {

        var loggedInUser = authFacade.getLoggedInUser(authentication.getName());

        var exerciseLongValue = Long.valueOf(id);

        var exerciseIds = loggedInUser.getExerciseList().stream()
                .map(Exercise::getId)
                .collect(Collectors.toList());

        if (!exerciseIds.contains(exerciseLongValue)) {
            throw new IllegalArgumentException("user does not have an exercise with id " + exerciseLongValue);
        }

        var optionalExerciseActivity = exerciseActivityService.createNewExerciseActivity(id, createExerciseActivityDto);

        if (optionalExerciseActivity.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(optionalExerciseActivity.get());
        }

    }


    @GetMapping("/{id}/progress")
    public ResponseEntity getProgress(
            Authentication authentication,
            @PathVariable(value = "id") String id,
            @RequestBody ProgressDto progressDto) {

        var loggedInUser = authFacade.getLoggedInUser(authentication.getName());


        var exerciseLongValue = Long.valueOf(id);

        var exerciseIds = loggedInUser.getExerciseList().stream()
                .map(Exercise::getId)
                .collect(Collectors.toList());

        if (!exerciseIds.contains(exerciseLongValue)) {
            throw new IllegalArgumentException("user does not have an exercise with id " + exerciseLongValue);
        }

        var progress = statService.getProgress(progressDto, loggedInUser.getId(), id);

        return ResponseEntity.ok(progress);


    }

    @GetMapping("/{id}/average")
    public ResponseEntity getAverage(
            Authentication authentication,
            @PathVariable(value = "id") String id,
            @RequestBody AverageDto averageDto) {


        var loggedInUser = authFacade.getLoggedInUser(authentication.getName());


        var exerciseLongValue = Long.valueOf(id);

        var exerciseIds = loggedInUser.getExerciseList().stream()
                .map(Exercise::getId)
                .collect(Collectors.toList());

        if (!exerciseIds.contains(exerciseLongValue)) {
            throw new IllegalArgumentException("user does not have an exercise with id " + exerciseLongValue);
        }


        var average = statService.getAverage(averageDto, loggedInUser.getId(),id);

        return ResponseEntity.ok(average);


    }
}
