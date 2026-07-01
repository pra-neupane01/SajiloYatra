package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.RegisterRequest;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.dto.response.UserResponseDto;
import in.neuprakash.SajiloYatra.entity.User;
import in.neuprakash.SajiloYatra.entity.enums.RoleEnum;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.UserMapper;
import in.neuprakash.SajiloYatra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDto saveUser(RegisterRequest request) {
        checkUserExists(request);
        
        User user = UserMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.password()));

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

    public UserResponseDto updateUser(Long id, RegisterRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("user not found with the provided id"));

        validate(request, existingUser);

        return UserMapper.toResponse(userRepository.save(existingUser));
    }

    private void validate(RegisterRequest request, User existingUser) {
        if (request.fullName() != null) {
            existingUser.setFullName(request.fullName());
        }
        if (request.address() != null) {
            existingUser.setAddress(request.address());
        }
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("user not found with the provided id"));
        userRepository.delete(user);
    }

    private void checkUserExists(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("user already exists with the provided email");
        }
    }
}

