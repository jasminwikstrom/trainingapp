package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewMachineDto;
import se.jasmin.exjobb.trainapp.repository.MachineRepository;
import se.jasmin.exjobb.trainapp.repository.entity.Machine;

import java.util.List;


@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public Machine createMachine(CreateNewMachineDto createNewMachineDto) {

        if (createNewMachineDto.getName() == null) {
            throw new RuntimeException("Title can not be null");
        }

        var newMachine = new Machine();
        newMachine.setName(createNewMachineDto.getName());
        newMachine.setMuscleGroup(createNewMachineDto.getMuscleGroup());
        newMachine.setDescription(createNewMachineDto.getDescription());
        newMachine.setMachineActivityList(List.of());

        return machineRepository.save(newMachine);
    }

    @Override
    public List<Machine> getMachines(String name) {

        List<Machine> machines = machineRepository.findByQuery(name);
        return machines;
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
