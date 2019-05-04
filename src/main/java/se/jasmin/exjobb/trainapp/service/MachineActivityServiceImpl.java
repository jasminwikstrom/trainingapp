package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateMachineActivityDto;
import se.jasmin.exjobb.trainapp.repository.MachineRepository;
import se.jasmin.exjobb.trainapp.repository.entity.MachineActivity;

import java.util.Comparator;
import java.util.Optional;

@Service
public class MachineActivityServiceImpl implements MachineActivityService {

    @Autowired
    private MachineRepository machineRepository;

    @Override
    public Optional<MachineActivity> createNewMachineActivity(String id, CreateMachineActivityDto createMachineActivityDto) {
        var machine = machineRepository.findById(Long.parseLong(id));

        if (machine.isEmpty()) {
            return Optional.empty();
        }

        var foundMachine = machine.get();

        var newActivity = new MachineActivity();
        newActivity.setWeight(createMachineActivityDto.getWeight());

        foundMachine.getMachineActivityList().add(newActivity);
        var savedMachine = machineRepository.save(foundMachine);

        var savedActivity = savedMachine.getMachineActivityList().stream()
                .max(Comparator.comparing(MachineActivity::getCreated))
                .orElse(null);

        return Optional.ofNullable(savedActivity);
    }
}
