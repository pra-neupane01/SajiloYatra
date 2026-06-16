package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.PaginationRequest;
import in.neuprakash.SajiloYatra.dto.request.UserRequestDto;
import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<APIResponse<PagedResponse<UserResponseDto>>> getAllUsers(@ModelAttribute PaginationRequest paginationRequest) {

        PagedResponse<UserResponseDto> allUsers = userService.getAllUsers(paginationRequest.toPageable());
        APIResponse<PagedResponse<UserResponseDto>> apiResponse = APIResponse.<PagedResponse<UserResponseDto>>builder()
                .status(true)
                .message("User fetched successfully")
                .data(allUsers)
                .build();
        return ResponseEntity.ok(apiResponse);

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
