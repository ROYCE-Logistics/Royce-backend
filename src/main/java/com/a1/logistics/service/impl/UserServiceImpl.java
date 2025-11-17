package com.a1.logistics.service.impl;

import com.a1.logistics.dto.user.UserCreateRequest;
import com.a1.logistics.dto.user.UserDto;
import com.a1.logistics.entity.User;
import com.a1.logistics.repository.UserRepository;
import com.a1.logistics.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserDto> list(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public UserDto get(Long id) {
        return userRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public UserDto create(UserCreateRequest request) {
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .passwordHash(passwordEncoder.encode(request.password()))
                .role(request.role())
                .status(request.status())
                .build();
        return toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(Long id, UserCreateRequest request) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        existing.setName(request.name());
        existing.setEmail(request.email());
        existing.setPhone(request.phone());
        existing.setRole(request.role());
        existing.setStatus(request.status());
        if (request.password() != null && !request.password().isBlank()) {
            existing.setPasswordHash(passwordEncoder.encode(request.password()));
        }
        return toDto(userRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole(), user.getStatus());
    }
}
