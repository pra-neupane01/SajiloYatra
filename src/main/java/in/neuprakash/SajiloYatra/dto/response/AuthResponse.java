package in.neuprakash.SajiloYatra.dto.response;

import in.neuprakash.SajiloYatra.entity.enums.RoleEnum;
import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        Long userId,
        String fullName,
        String email,
        RoleEnum role
) {
}