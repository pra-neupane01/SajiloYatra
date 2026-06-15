package in.neuprakash.SajiloYatra.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PassengerResponseDto(Long id,
                                   Long userId,
                                   String citizenshipNo,
                                   String preferences,
                                   LocalDateTime createdAt,
                                   LocalDateTime updatedAt) {
}
