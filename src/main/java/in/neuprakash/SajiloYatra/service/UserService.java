package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.UserRequestDto;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.entity.User;
import in.neuprakash.SajiloYatra.entity.enums.RoleEnum;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.UserMapper;
import in.neuprakash.SajiloYatra.repository.UserRepository;
import in.neuprakash.SajiloYatra.util.HashUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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


    public PagedResponse<UserResponseDto> getAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserResponseDto> userResponsePage = userPage.map(UserMapper::toResponse);
        return PagedResponse.from(userResponsePage);
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("user not found with the provided id"));
        return UserMapper.toResponse(user);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("user not found with the provided id"));

        validate(userRequestDto, existingUser);

        return UserMapper.toResponse(userRepository.save(existingUser));
    }

    private void validate(UserRequestDto userRequestDto, User existingUser) {
        if (userRequestDto.fullName() != null) {
            existingUser.setFullName(userRequestDto.fullName());
        }
        if (userRequestDto.address() != null) {
            existingUser.setAddress(userRequestDto.address());
        }
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("user not found with the provided id"));
        userRepository.delete(user);
    }
}

