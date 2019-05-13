package se.jasmin.exjobb.trainapp.repository.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "MACHINE")
public class Machine {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column (name = "MUSCLE_GROUP")
    @Enumerated(value = EnumType.STRING)
    private MuscleGroup muscleGroup;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MACHINE_ID", referencedColumnName = "ID", nullable = false)
    private List<MachineActivity> machineActivityList;

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

    public List<MachineActivity> getMachineActivityList() {
        return machineActivityList;
    }

    public void setMachineActivityList(List<MachineActivity> machineActivityList) {
        this.machineActivityList = machineActivityList;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", muscleGroup=" + muscleGroup +
                ", machineActivityList=" + machineActivityList +
                '}';
    }
}
