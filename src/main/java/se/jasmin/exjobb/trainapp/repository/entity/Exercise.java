package se.jasmin.exjobb.trainapp.repository.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "exercise")
public class Exercise {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column (name = "muscle_group")
    @Enumerated(value = EnumType.STRING)
    private MuscleGroup muscleGroup;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable = false)
    private List<ExerciseActivity> exerciseActivityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ExerciseActivity> getExerciseActivityList() {
        return exerciseActivityList;
    }

    public void setExerciseActivityList(List<ExerciseActivity> exerciseActivityList) {
        this.exerciseActivityList = exerciseActivityList;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", muscleGroup=" + muscleGroup +
                ", exerciseActivityList=" + exerciseActivityList +
                '}';
    }
}
