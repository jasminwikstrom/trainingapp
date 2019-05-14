package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewExerciseDto;
import se.jasmin.exjobb.trainapp.repository.ExerciseRepository;
import se.jasmin.exjobb.trainapp.repository.UserRepository;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Optional<Exercise> createExercise(CreateNewExerciseDto createNewExerciseDto) {

        if (createNewExerciseDto.getName() == null) {
            throw new RuntimeException("Title can not be null");
        }

        var user = userRepository.findById(Long.valueOf(createNewExerciseDto.getUserId()));

        if (user.isPresent()) {

            var newExercise = new Exercise();
            newExercise.setName(createNewExerciseDto.getName());
            newExercise.setDescription(createNewExerciseDto.getDescription());
            newExercise.setMuscleGroup(createNewExerciseDto.getMuscleGroup());



            user.get().getExerciseList().add(newExercise);
            var savedUser = userRepository.save(user.get());

            var addedExercice = savedUser.getExerciseList().stream()
                    .max(Comparator.comparing(Exercise::getCreated));

            return addedExercice;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Exercise> getExercises(String name) {

        List<Exercise> exercises = exerciseRepository.findByQuery(name);
        return exercises;
    }





    //@Override
    //public Optional<Machine> updateMachine(String id, UpdateMachineDto updateMachineDto) {
    //    Optional<Machine> foundMachine = machineRepository.findById(Long.parseLong(id));
//
    //    return foundMachine.map(machine -> {
//
    //        if (updateMachineDto.getDescription() != null) {
    //            machine.setDescription(updateMachineDto.getDescription());
    //            return machineRepository.save(machine);
    //        }
    //        return machine;
    //    });
    //}


}