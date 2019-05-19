package se.jasmin.exjobb.trainapp.repository.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "exercise")
@EntityListeners(AuditingEntityListener.class)
public class Exercise {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "muscle_group")
    @Enumerated(value = EnumType.STRING)
    private MuscleGroup muscleGroup;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable = false)
    private List<ExerciseActivity> exerciseActivityList;

    @Column(name = "date", nullable = false)
    @CreatedDate
    private LocalDateTime created;

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(id, exercise.id) &&
                Objects.equals(name, exercise.name) &&
                Objects.equals(description, exercise.description) &&
                muscleGroup == exercise.muscleGroup &&
                Objects.equals(exerciseActivityList, exercise.exerciseActivityList) &&
                Objects.equals(created, exercise.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, muscleGroup, exerciseActivityList, created);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", muscleGroup=" + muscleGroup +
                ", exerciseActivityList=" + exerciseActivityList +
                ", created=" + created +
                '}';
    }
}
