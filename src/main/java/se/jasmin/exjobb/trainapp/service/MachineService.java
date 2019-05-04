package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.CreateNewMachineDto;
import se.jasmin.exjobb.trainapp.repository.entity.Machine;
import se.jasmin.exjobb.trainapp.repository.entity.MachineActivity;

import java.util.List;



public interface MachineService {
        Machine createMachine(CreateNewMachineDto createNewMachineDto);

        List<Machine> getMachines(String name);



    }

