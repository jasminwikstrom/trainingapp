package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateExerciseActivityDto;
import se.jasmin.exjobb.trainapp.repository.ExerciseRepository;
import se.jasmin.exjobb.trainapp.repository.entity.ExerciseActivity;
import se.jasmin.exjobb.trainapp.repository.entity.User;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseActivityServiceImpl implements ExerciseActivityService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Optional<ExerciseActivity> createNewExerciseActivity(String id, CreateExerciseActivityDto createExerciseActivityDto) {
        var exercise = exerciseRepository.findById(Long.parseLong(id));

        if (exercise.isEmpty()) {
            return Optional.empty();
        }

        var foundExercise = exercise.get();

        var newActivity = new ExerciseActivity();
        newActivity.setWeight(createExerciseActivityDto.getWeight());

        foundExercise.getExerciseActivityList().add(newActivity);
        var savedExercise = exerciseRepository.save(foundExercise);

        var savedActivity = savedExercise.getExerciseActivityList().stream()
                .max(Comparator.comparing(ExerciseActivity::getCreated))
                .orElse(null);

        return Optional.ofNullable(savedActivity);
    }




}