package org.myapps.pollutionfinder.user.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.myapps.pollutionfinder.user.ResourceNotFoundException;
import org.myapps.pollutionfinder.user.model.UserProfile;
import org.myapps.pollutionfinder.user.model.UserProfileRepository;
import org.myapps.pollutionfinder.user.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserResource {

    private final UserProfileRepository userProfileRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfile registerUser(@Valid @RequestBody UserRequest userRequest){
        UserProfile userProfile = mapUserRequest(userRequest);
        return userProfileRepository.save(userProfile);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        UserProfile userProfile = userProfileRepository.findByUsernameAndPassword(loginRequest.getUsername(),loginRequest.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Login details are incorrect !"));

        return new LoginResponse(JwtUtil.generateToken(userProfile.getUsername()));
    }
    private static UserProfile mapUserRequest(UserRequest userRequest) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(userRequest.getEmail());
        userProfile.setName(userRequest.getName());
        userProfile.setPassword(userRequest.getPassword());
        userProfile.setUsername(userRequest.getUsername());
        return userProfile;
    }
    
}
