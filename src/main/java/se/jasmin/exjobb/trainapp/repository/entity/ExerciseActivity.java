package se.jasmin.exjobb.trainapp.repository.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "exercise_activity")
public class ExerciseActivity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "date", nullable = false)
    @CreatedDate
    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
        ExerciseActivity that = (ExerciseActivity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight, created);
    }

    @Override
    public String toString() {
        return "ExerciseActivity{" +
                "id=" + id +
                ", weight='" + weight + '\'' +
                ", created=" + created +
                '}';
    }
}

