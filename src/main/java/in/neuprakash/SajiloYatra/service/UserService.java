package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.UserRequestDto;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.entity.User;
import in.neuprakash.SajiloYatra.entity.enums.RoleEnum;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.UserMapper;
import in.neuprakash.SajiloYatra.repository.UserRepository;
import in.neuprakash.SajiloYatra.utils.HashUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HashUtils passwordEncoder;


    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        if (userRepository.existsByEmail(userRequestDto.email())) {
            throw new BusinessException("user already exists with the provided email");
        }
        User user = UserMapper.toEntity(userRequestDto);

        user.setPassword(passwordEncoder.encode(userRequestDto.password()));

        user.setRole(RoleEnum.PASSENGER);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }


    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper::toResponse).toList();

    }

}























