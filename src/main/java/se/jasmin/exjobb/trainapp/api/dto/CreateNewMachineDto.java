package se.jasmin.exjobb.trainapp.api.dto;

import se.jasmin.exjobb.trainapp.repository.entity.MuscleGroup;

public class CreateNewMachineDto {


    private String name;
    private String description;
    private MuscleGroup muscleGroup;


    public CreateNewMachineDto() {
    }

    public CreateNewMachineDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }


    @Override
    public String toString() {
        return "CreateNewMachineDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", muscleGroup=" + muscleGroup +
                '}';
    }
}
