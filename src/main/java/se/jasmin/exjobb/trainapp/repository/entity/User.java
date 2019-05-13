package se.jasmin.exjobb.trainapp.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "goals")
    private String goals;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private List<WeightTrack> weightTrackList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public List<WeightTrack> getWeightTrackList() {
        return weightTrackList;
    }

    public void setWeightTrackList(List<WeightTrack> weightTrackList) {
        this.weightTrackList = weightTrackList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", goals='" + goals + '\'' +
                ", weightTrackList=" + weightTrackList +
                '}';
    }
}
