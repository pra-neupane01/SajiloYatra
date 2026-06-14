package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.UserRequestDto;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto request) {
        return userService.saveUser(request);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUserById(@PathVariable Long id,
                                          @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return "user deleted successfully";
    }
}
