package se.jasmin.exjobb.trainapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jasmin.exjobb.trainapp.api.dto.CreateMachineActivityDto;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewMachineDto;
import se.jasmin.exjobb.trainapp.repository.entity.Machine;
import se.jasmin.exjobb.trainapp.service.MachineActivityService;
import se.jasmin.exjobb.trainapp.service.MachineService;


@RestController
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineActivityService machineActivityService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Machine createNewMachine(@RequestBody CreateNewMachineDto createNewMachineDto) {

        var savedMachine = machineService.createMachine(createNewMachineDto);

        return ResponseEntity.ok(savedMachine).getBody();
    }


    @GetMapping
    public Machine getAllMachine(@RequestParam(value = "name", required = false) String title) {

        Machine machines = (Machine) machineService.getMachines(title);

        return machines;
    }

    @PostMapping("/{id}/machineactivities")
    public ResponseEntity createNewMachineActivity(
            @PathVariable(value = "id") String id,
            @RequestBody CreateMachineActivityDto createMachineActivityDto) {

        var optionalMachineActivity = machineActivityService.createNewMachineActivity(id, createMachineActivityDto);

        if (optionalMachineActivity.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(optionalMachineActivity.get());
        }
    }
}
