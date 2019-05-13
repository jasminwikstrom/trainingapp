package se.jasmin.exjobb.trainapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewWeightTrackDto;
import se.jasmin.exjobb.trainapp.repository.UserRepository;
import se.jasmin.exjobb.trainapp.repository.WeightTrackRepository;
import se.jasmin.exjobb.trainapp.repository.entity.WeightTrack;

import java.util.Comparator;
import java.util.Optional;

@Service
public class WeightTrackServiceImpl implements WeightTrackService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<WeightTrack> createNewWeightTrack(String id, CreateNewWeightTrackDto createNewWeightTrackDto) {
        var user =userRepository.findById(Long.parseLong(id));

        if (user.isEmpty()) {
            return Optional.empty();
        }


        var foundUser = user.get();

        var newWeightTrack = new WeightTrack();
        newWeightTrack.setWeight(createNewWeightTrackDto.getWeight());

        foundUser.getWeightTrackList().add(newWeightTrack);
        var savedUser = userRepository.save(foundUser);

        var savedWeightTrack = savedUser.getWeightTrackList().stream()
                .max(Comparator.comparing(WeightTrack::getCreated))
                .orElse(null);
        return Optional.ofNullable(savedWeightTrack);




}
}