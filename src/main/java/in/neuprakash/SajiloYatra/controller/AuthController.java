package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.LoginRequest;
import in.neuprakash.SajiloYatra.dto.request.RegisterRequest;
import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.AuthResponse;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserResponseDto>> registerUser(
            @Valid @RequestBody RegisterRequest request
    ) {
        UserResponseDto registeredUser = authService.registerUser(request);

        APIResponse<UserResponseDto> apiResponse = APIResponse.<UserResponseDto>builder()
                .success(true)
                .message("User registered successfully")
                .data(registeredUser)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<AuthResponse>> loginUser(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        AuthResponse authResponse = authService.loginUser(loginRequest);

        APIResponse<AuthResponse> apiResponse = APIResponse.<AuthResponse>builder()
                .success(true)
                .message("User logged in successfully")
                .data(authResponse)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}