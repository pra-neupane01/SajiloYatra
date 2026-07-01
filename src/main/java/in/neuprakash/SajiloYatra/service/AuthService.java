package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.LoginRequest;
import in.neuprakash.SajiloYatra.dto.request.RegisterRequest;
import in.neuprakash.SajiloYatra.dto.response.AuthResponse;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.entity.User;
import in.neuprakash.SajiloYatra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    pri



    public UserResponseDto registerUser(RegisterRequest request) {
        return userService.saveUser(request);
    }

    public AuthResponse loginUser(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        User user = userRepository.findByEmail(request.email());

        String token = JwtService

    }
}
