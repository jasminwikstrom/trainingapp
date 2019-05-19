package se.jasmin.exjobb.trainapp.api.dto;

import se.jasmin.exjobb.trainapp.repository.entity.MuscleGroup;

import java.util.Objects;

public class CreateNewExerciseDto {


    private String name;
    private String description;
    private MuscleGroup muscleGroup;


    public CreateNewExerciseDto() {
    }

    public CreateNewExerciseDto(String name) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateNewExerciseDto that = (CreateNewExerciseDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                muscleGroup == that.muscleGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, muscleGroup);
    }

    @Override
    public String toString() {
        return "CreateNewExerciseDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", muscleGroup=" + muscleGroup +
                '}';
    }
}
