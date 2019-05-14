package se.jasmin.exjobb.trainapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jasmin.exjobb.trainapp.api.dto.*;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import se.jasmin.exjobb.trainapp.service.ExerciseActivityService;
import se.jasmin.exjobb.trainapp.service.ExerciseService;
import se.jasmin.exjobb.trainapp.service.StatService;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseActivityService exerciseActivityService;

    @Autowired
    private StatService statService;




    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Exercise> createNewExercise(@RequestBody CreateNewExerciseDto createNewExerciseDto) {

        var savedExercise = exerciseService.createExercise(createNewExerciseDto);

        return ResponseEntity.ok(savedExercise).getBody();
    }


    @GetMapping
    public Exercise getAllExercises(@RequestParam(value = "name", required = false) String title) {

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


    @GetMapping ("/{id}/progress")
    public ResponseEntity getProgress(
            @PathVariable(value = "id") String id,
            @RequestBody ProgressDto progressDto) {

        var progress = statService.getProgress(progressDto, id);

        return ResponseEntity.ok(progress);


    }

        @GetMapping ("/{id}/average")
        public ResponseEntity getAverage(
                @PathVariable(value = "id") String id,
                @RequestBody AverageDto averageDto) {

            var average = statService.getAverage(averageDto, id);

            return ResponseEntity.ok(average);


        }







    //GetStatsAverage
    //GetHistory
}
