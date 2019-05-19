package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.CreateNewWeightTrackDto;
import se.jasmin.exjobb.trainapp.repository.entity.WeightTrack;

import java.util.Optional;

public interface WeightTrackService {

    Optional<WeightTrack> createNewWeightTrack(Long id, CreateNewWeightTrackDto createNewWeightTrackDto);
}