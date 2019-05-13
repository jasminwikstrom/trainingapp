package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.CreateNewExercisDto;
import se.jasmin.exjobb.trainapp.repository.entity.Exercis;
import se.jasmin.exjobb.trainapp.repository.entity.ExercisActivity;

import java.util.List;



public interface ExercisService {
    Exercis createExercis(CreateNewExercisDto createNewExercisDto);

    List<Exercis> getExercise(String name);



}