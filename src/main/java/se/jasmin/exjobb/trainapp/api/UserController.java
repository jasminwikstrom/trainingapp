package se.jasmin.exjobb.trainapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.jasmin.exjobb.trainapp.api.dto.CreateNewWeightTrackDto;
import se.jasmin.exjobb.trainapp.repository.entity.User;
import se.jasmin.exjobb.trainapp.service.AuthFacade;
import se.jasmin.exjobb.trainapp.service.SecurityService;
import se.jasmin.exjobb.trainapp.service.UserService;
import se.jasmin.exjobb.trainapp.service.WeightTrackService;
import se.jasmin.exjobb.trainapp.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private WeightTrackService weightTrackService;

    @Autowired
    private AuthFacade authFacade;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }


    @PostMapping("/weights")
    public ResponseEntity createNewWeightTrack(
            Authentication authentication,
            @RequestBody CreateNewWeightTrackDto createWeightTrackDto) {

        var loggedInUser = authFacade.getLoggedInUser(authentication.getName());

        var optionalWeightTrack = weightTrackService.createNewWeightTrack(loggedInUser.getId(), createWeightTrackDto);

        if (optionalWeightTrack.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(optionalWeightTrack.get());
        }
    }

}
