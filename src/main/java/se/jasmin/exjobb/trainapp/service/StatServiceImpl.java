package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.AverageDto;
import se.jasmin.exjobb.trainapp.api.dto.AverageStat;
import se.jasmin.exjobb.trainapp.api.dto.ProgressDto;
import se.jasmin.exjobb.trainapp.api.dto.ProgressStat;
import se.jasmin.exjobb.trainapp.repository.UserRepository;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import se.jasmin.exjobb.trainapp.repository.entity.ExerciseActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private UserRepository userRepository;

    @Override
    public ProgressStat getProgress(ProgressDto progressDto, Long userId, String id) {

        var exerciseActivityList = filterActivitiesByInterval(userId, id, progressDto.getFrom(), progressDto.getTo());

        if (exerciseActivityList.size() == 1) {
            var progressStat = new ProgressStat();
            progressStat.setStartWeight(exerciseActivityList.get(0).getWeight());
            progressStat.setDiff(0);
        }

        var firstActivity = exerciseActivityList.stream()
                .min(Comparator.comparing(ExerciseActivity::getCreated));

        var lastActivity = exerciseActivityList.stream()
                .max(Comparator.comparing(ExerciseActivity::getCreated));

        var diff = lastActivity.get().getWeight() - firstActivity.get().getWeight();

        var progressStat = new ProgressStat();
        progressStat.setStartWeight(firstActivity.get().getWeight());
        progressStat.setEndWeight(lastActivity.get().getWeight());
        progressStat.setDiff(diff);

        return progressStat;
    }

    @Override
    public AverageStat getAverage(AverageDto averageDto, Long userId, String id) {

        var exerciseActivityList = filterActivitiesByInterval(userId, id, averageDto.getFrom(), averageDto.getTo());

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

        double avg = (double) allActivityWeight / (double) exerciseActivityList.size();

        var averageStat = new AverageStat();
        averageStat.setStartWeight(firstActivity.get().getWeight());
        averageStat.setStartWeight(lastActivity.get().getWeight());
        averageStat.setAverage(avg);

        return averageStat;

    }

    @Override
    public Exercise getHistory(long userId, String id) {
        return filterExerciseForUser(userId, id);
    }

    private Exercise filterExerciseForUser(long userId, String exerciseId) {

        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("user with id " + userId + " does not exist");
        }

        var foundUser = user.get();

        var exercises = foundUser.getExerciseList().stream()
                .filter(exercise -> exercise.getId() == Long.parseLong(exerciseId))
                .collect(Collectors.toList());
        if (exercises.size() != 1) {
            throw new IllegalArgumentException("exercise with id " + exerciseId + " does not exist");
        }

        return exercises.get(0);
    }

    private List<ExerciseActivity> filterActivitiesByInterval(long userId, String exerciseId, String fromString, String toString) {

        var from = LocalDateTime.parse(fromString, FORMATTER);
        var to = LocalDateTime.parse(toString, FORMATTER);

        var exercise = filterExerciseForUser(userId, exerciseId);

        return exercise.getExerciseActivityList().stream()
                .filter(activity -> !activity.getCreated().isBefore(from))
                .filter(activity -> !activity.getCreated().isAfter(to))
                .collect(Collectors.toList());
    }
}









