package se.jasmin.exjobb.trainapp.service;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewExerciseDto;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import java.util.List;



public interface ExerciseService {
    Exercise createExercise(CreateNewExerciseDto createNewExerciseDto);

    List<Exercise> getExercises(String name);



}