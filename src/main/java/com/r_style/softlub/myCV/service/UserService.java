package com.r_style.softlub.myCV.service;

import com.r_style.softlub.myCV.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto getUserById(Long id);
    UserDto saveUser(UserDto userDto);
    void deleteUser(Long id);
}
