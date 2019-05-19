package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.CreateNewExerciseDto;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import se.jasmin.exjobb.trainapp.repository.entity.User;

import java.util.Optional;

public interface ExerciseService {
    Optional<Exercise> createExercise(User user, CreateNewExerciseDto createNewExerciseDto);

}