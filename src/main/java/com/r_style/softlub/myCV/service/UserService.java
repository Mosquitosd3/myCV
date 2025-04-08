package com.r_style.softlub.myCV.service;

import com.r_style.softlub.myCV.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto saveUser(UserDto userDto);
    void deleteUser(Long id);
}
