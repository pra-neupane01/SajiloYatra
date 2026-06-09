package in.neuprakash.SajiloYatra.dto.response;

import in.neuprakash.SajiloYatra.entity.enums.RoleEnum;
import lombok.Builder;

@Builder
public record UserResponseDto(Long id,
                              String fullName,
                              String email,
                              String address,
                              RoleEnum roleEnum) {
    
}
