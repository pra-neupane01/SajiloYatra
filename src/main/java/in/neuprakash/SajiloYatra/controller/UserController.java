package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.PaginationRequest;
import in.neuprakash.SajiloYatra.dto.request.RegisterRequest;
import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody RegisterRequest request) {
        return userService.saveUser(request);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public ResponseEntity<APIResponse<PagedResponse<UserResponseDto>>> getAllUsers(@ModelAttribute PaginationRequest paginationRequest) {

        PagedResponse<UserResponseDto> allUsers = userService.getAllUsers(paginationRequest.toPageable());
        APIResponse<PagedResponse<UserResponseDto>> apiResponse = APIResponse.<PagedResponse<UserResponseDto>>builder()
                .success(true)
                .message("User fetched successfully")
                .data(allUsers)
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public UserResponseDto getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public UserResponseDto updateUserById(@PathVariable Long id,
                                          @RequestBody RegisterRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return "user deleted successfully";
    }
}
