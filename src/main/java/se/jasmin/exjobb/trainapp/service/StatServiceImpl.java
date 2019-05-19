package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.AverageDto;
import se.jasmin.exjobb.trainapp.api.dto.AverageStat;
import se.jasmin.exjobb.trainapp.api.dto.ProgressDto;
import se.jasmin.exjobb.trainapp.api.dto.ProgressStat;
import se.jasmin.exjobb.trainapp.repository.UserRepository;
import se.jasmin.exjobb.trainapp.repository.entity.ExerciseActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ProgressStat getProgress(ProgressDto progressDto, Long userId, String id) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        var from = LocalDateTime.parse(progressDto.getFrom(), formatter);
        var to = LocalDateTime.parse(progressDto.getTo(), formatter);

        var user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        var foundUser = user.get();

        var exercises = foundUser.getExerciseList().stream()
                .filter(exercise -> exercise.getId() == Long.parseLong(id))
                .collect(Collectors.toList());
        if (exercises.size() != 1) {
            return null;
        }

        var exercise = exercises.get(0);

        var exerciseActivityList = exercise.getExerciseActivityList().stream()
                .filter(activity -> !activity.getCreated().isBefore(from))
                .filter(activity -> !activity.getCreated().isAfter(to) )
                .collect(Collectors.toList());

        if (exerciseActivityList.isEmpty()) {
            return null;
        }

        if (exerciseActivityList.size() == 1) {
            var progressStat = new ProgressStat();
            progressStat.setStartWeight(exerciseActivityList.get(0).getWeight());
            progressStat.setDiff(0);
        }

        var firstActivity = exerciseActivityList.stream()
                .min(Comparator.comparing(ExerciseActivity::getCreated));

        var lastActivity = exerciseActivityList.stream()
                .max(Comparator.comparing(ExerciseActivity::getCreated));

        var diff = lastActivity.get().getWeight() - firstActivity.get().getWeight() ;

        var progressStat = new ProgressStat();
        progressStat.setStartWeight(firstActivity.get().getWeight());
        progressStat.setEndWeight(lastActivity.get().getWeight());
        progressStat.setDiff(diff);

        return progressStat;
    }


    @Override
    public AverageStat getAverage(AverageDto averageDto,Long userId, String id) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        var from = LocalDateTime.parse(averageDto.getFrom(), formatter);
        var to = LocalDateTime.parse(averageDto.getTo(), formatter);

        var user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        var foundUser = user.get();

        var exercises = foundUser.getExerciseList().stream()
                .filter(exercise -> exercise.getId() == Long.parseLong(id))
                .collect(Collectors.toList());
        if (exercises.size() != 1) {
            return null;
        }

        var exercise = exercises.get(0);

        var exerciseActivityList = exercise.getExerciseActivityList().stream()
                .filter(activity -> !activity.getCreated().isBefore(from))
                .filter(activity -> !activity.getCreated().isAfter(to) )
                .collect(Collectors.toList());

        if (exerciseActivityList.isEmpty()) {
            return null;
        }

        if (exerciseActivityList.size() == 1) {
            var averageStat = new AverageStat();
            averageStat.setStartWeight(exerciseActivityList.get(0).getWeight());
            averageStat.setAverage(0);
        }

        var allActivityWeight = exerciseActivityList.stream()
                .collect(Collectors.summingInt(ExerciseActivity::getWeight));

        var firstActivity = exerciseActivityList.stream()
                .min(Comparator.comparing(ExerciseActivity::getCreated));

        var lastActivity = exerciseActivityList.stream()
                .max(Comparator.comparing(ExerciseActivity::getCreated));

        double avg = (double)allActivityWeight / (double)exerciseActivityList.size();



        var averageStat = new AverageStat();
        averageStat.setStartWeight(firstActivity.get().getWeight());
        averageStat.setStartWeight(lastActivity.get().getWeight());
        averageStat.setAverage(avg);



        return averageStat;

    }
}


