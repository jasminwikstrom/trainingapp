package se.jasmin.exjobb.trainapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewUserDto;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewWeightTrackDto;
import se.jasmin.exjobb.trainapp.repository.entity.User;
import se.jasmin.exjobb.trainapp.service.UserService;
import se.jasmin.exjobb.trainapp.service.WeightTrackService;


@RestController
@RequestMapping("/weight")
public class WeightController {

    @Autowired
    private WeightTrackService weightTrackService;

    @Autowired
    private UserService userService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createNewUser(@RequestBody CreateNewUserDto createNewUserDto) {

        var savedUser = userService.createUser(createNewUserDto);

        return ResponseEntity.ok(savedUser).getBody();
    }


    @GetMapping("/allusers")
    public User getAllUsers(@RequestParam(value = "username", required = false) String username) {

        User users = (User) userService.getUsers(username);
        return users;
    }






    @PostMapping("/{id}/weighttrack")
    public ResponseEntity createNewWeightTrack(
            @PathVariable(value = "id") String id,
            @RequestBody CreateNewWeightTrackDto createWeightTrackDto) {

        var optionalWeightTrack = weightTrackService.createNewWeightTrack(id, createWeightTrackDto);

        if (optionalWeightTrack.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(optionalWeightTrack.get());
        }
    }
}
