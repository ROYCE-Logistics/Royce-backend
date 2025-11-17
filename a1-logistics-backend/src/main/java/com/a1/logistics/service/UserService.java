package com.a1.logistics.service;

import com.a1.logistics.dto.user.UserCreateRequest;
import com.a1.logistics.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> list(Pageable pageable);
    UserDto get(Long id);
    UserDto create(UserCreateRequest request);
    UserDto update(Long id, UserCreateRequest request);
    void delete(Long id);
}
