package in.neuprakash.SajiloYatra.dto.response;

public record AuthResponse(String token,
                           Long userId,
                           String email,
                           String password) {
}
