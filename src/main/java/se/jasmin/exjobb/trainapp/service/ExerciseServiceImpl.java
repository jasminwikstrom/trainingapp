package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewExerciseDto;
import se.jasmin.exjobb.trainapp.repository.ExerciseRepository;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;

import java.util.List;


@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public Exercise createExercise(CreateNewExerciseDto createNewExerciseDto) {

        if (createNewExerciseDto.getName() == null) {
            throw new RuntimeException("Title can not be null");
        }

        var newExercise = new Exercise();
        newExercise.setName(createNewExerciseDto.getName());
        newExercise.setMuscleGroup(createNewExerciseDto.getMuscleGroup());
        newExercise.setDescription(createNewExerciseDto.getDescription());
        newExercise.setExerciseActivityList(List.of());

        return exerciseRepository.save(newExercise);
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