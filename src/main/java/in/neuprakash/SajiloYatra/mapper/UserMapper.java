package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.request.UserRequestDto;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.entity.User;

public class UserMapper {
    public static User toEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .email(userRequestDto.email())
                .fullName(userRequestDto.fullName())
                .address(userRequestDto.address())
                .build();
    }

    public static UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .fullName(user.getFullName())
                .id(user.getId())
                .address(user.getAddress())
                .roleEnum(user.getRole())
                .email(user.getEmail())
                .build();

    }
}
