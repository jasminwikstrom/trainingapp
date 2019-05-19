package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.CreateExerciseActivityDto;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import se.jasmin.exjobb.trainapp.repository.entity.ExerciseActivity;

import java.util.List;
import java.util.Optional;

public interface ExerciseActivityService {

    Optional<ExerciseActivity> createNewExerciseActivity(String id, CreateExerciseActivityDto createExerciseActivityDto);

}