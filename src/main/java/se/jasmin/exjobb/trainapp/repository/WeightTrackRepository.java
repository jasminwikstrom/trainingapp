package se.jasmin.exjobb.trainapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.jasmin.exjobb.trainapp.repository.entity.WeightTrack;

@Repository
public interface WeightTrackRepository extends JpaRepository<WeightTrack, Long> {

}