package com.r_style.softlub.myCV.service;

import com.r_style.softlub.myCV.dto.UserDto;
import com.r_style.softlub.myCV.exception.NotFoundException;
import com.r_style.softlub.myCV.model.User;
import com.r_style.softlub.myCV.repository.UserRepository;
import com.r_style.softlub.myCV.util.DtoUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepo;

    @Mock
    private DtoUtil dtoUtil;

    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getAllUsers_ReturnsPaginatedResult() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        User user = new User(1L, "John", "john@test.com", null, null, null);
        UserDto userDto = new UserDto(1L, "John", "john@test.com", null, null, null);

        when(userRepo.findAll(pageable)).thenReturn(new PageImpl<>(List.of(user)));
        when(dtoUtil.toUserDto(user)).thenReturn(userDto);

        Page<UserDto> result = userService.getAllUsers(pageable);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0)).isEqualTo(userDto);
        verify(userRepo).findAll(pageable);
    }

    @Test
    void getUserById_ReturnsUserDto() {
        // Arrange
        Long userId = 1L;
        User user = new User(userId, "John", "john@test.com", null, null, null);
        UserDto userDto = new UserDto(userId, "John", "john@test.com", null , null, null);

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(dtoUtil.toUserDto(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(userId);

        assertThat(result).isEqualTo(userDto);
        verify(userRepo).findById(userId);
    }

    @Test
    void getUserById_ThrowsWhenNotFound() {
        Long userId = 999L;
        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(userId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("User not found");
    }

    @Test
    void saveUser_ReturnsSavedUserDto() {
        // Arrange
        UserDto inputDto = new UserDto(null, "John", "john@test.com", null, null, null);
        User savedUser = new User(1L, "John", "john@test.com", null, null, null);
        UserDto expectedDto = new UserDto(1L, "John", "john@test.com", null, null, null);

        when(dtoUtil.toUserEntity(inputDto)).thenReturn(new User(null, "John", "john@test.com", null, null, null));
        when(userRepo.save(any(User.class))).thenReturn(savedUser);
        when(dtoUtil.toUserDto(savedUser)).thenReturn(expectedDto);

        // Act
        UserDto result = userService.saveUser(inputDto);

        // Assert
        assertThat(result).isEqualTo(expectedDto);
        verify(userRepo).save(any(User.class));
    }

    @Test
    void deleteUser_ExecutesWithoutErrors() {
        Long userId = 1L;
        doNothing().when(userRepo).deleteById(userId);
        assertThatNoException()
                .isThrownBy(() -> userService.deleteUser(userId));
        verify(userRepo).deleteById(userId);
    }
}