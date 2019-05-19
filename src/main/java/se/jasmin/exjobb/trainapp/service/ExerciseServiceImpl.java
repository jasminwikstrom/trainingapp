package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewExerciseDto;
import se.jasmin.exjobb.trainapp.repository.ExerciseRepository;
import se.jasmin.exjobb.trainapp.repository.UserRepository;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import se.jasmin.exjobb.trainapp.repository.entity.User;

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

    @Override
    public Optional<Exercise> createExercise(User user, CreateNewExerciseDto createNewExerciseDto) {

        if (createNewExerciseDto.getName() == null) {
            throw new RuntimeException("Title can not be null");
        }


        var newExercise = new Exercise();
        newExercise.setName(createNewExerciseDto.getName());
        newExercise.setDescription(createNewExerciseDto.getDescription());
        newExercise.setMuscleGroup(createNewExerciseDto.getMuscleGroup());


        user.getExerciseList().add(newExercise);
        var savedUser = userRepository.save(user);

        var addedExercice = savedUser.getExerciseList().stream()
                .max(Comparator.comparing(Exercise::getCreated));

        return addedExercice;

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