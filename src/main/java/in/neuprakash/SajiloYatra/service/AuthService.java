package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.LoginRequest;
import in.neuprakash.SajiloYatra.dto.request.RegisterRequest;
import in.neuprakash.SajiloYatra.dto.response.AuthResponse;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.repository.UserRepository;
import in.neuprakash.SajiloYatra.security.JwtService;
import in.neuprakash.SajiloYatra.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserResponseDto registerUser(RegisterRequest request) {
        return userService.saveUser(request);
    }

    public AuthResponse loginUser(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        String jwt = jwtService.generateToken(userPrincipal);

        return AuthResponse.builder()
                .token(jwt)
                .userId(userPrincipal.getId())
                .fullName(userPrincipal.getFullName())
                .email(userPrincipal.getEmail())
                .role(userPrincipal.getRole())
                .build();
    }
}