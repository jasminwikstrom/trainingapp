package se.jasmin.exjobb.trainapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.jasmin.exjobb.trainapp.repository.entity.Machine;

import java.util.List;

@Repository

  public interface MachineRepository extends JpaRepository<Machine, Long> {

        @Query(value =
                "SELECT b FROM Machine b " +

                        "WHERE (:name IS NULL OR b.name = :name) ")
        List<Machine> findByQuery(
                @Param("name") String name);

    }



