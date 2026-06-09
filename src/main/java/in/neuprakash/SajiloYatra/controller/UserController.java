package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.UserRequestDto;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto request) {
        return userService.saveUser(request);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();

    }
}
