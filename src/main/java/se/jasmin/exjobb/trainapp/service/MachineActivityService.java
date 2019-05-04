package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.CreateMachineActivityDto;
import se.jasmin.exjobb.trainapp.repository.entity.MachineActivity;

import java.util.Optional;

public interface MachineActivityService {

    Optional<MachineActivity> createNewMachineActivity(String id, CreateMachineActivityDto createMachineActivityDto);
}
