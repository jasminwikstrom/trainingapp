package se.jasmin.exjobb.trainapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.jasmin.exjobb.trainapp.repository.entity.Exercise;
import se.jasmin.exjobb.trainapp.repository.entity.ExerciseActivity;

import java.util.List;

@Repository

  public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

        @Query(value =
                "SELECT b FROM Exercise b " +

                        "WHERE (:name IS NULL OR b.name = :name) ")
        List<Exercise> findByQuery(
                @Param("name") String name);



    }



