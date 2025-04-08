package com.r_style.softlub.myCV.controller;

import com.r_style.softlub.myCV.dto.UserDto;
import com.r_style.softlub.myCV.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get paginated list of users")
    @GetMapping
    public Page<UserDto> getAllUsers( @Parameter(description = "Page number (0..N)") @RequestParam(defaultValue = "0") int page,
                                      @Parameter(description = "Items per page") @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public UserDto createUser(@RequestBody @Schema(example = "{\"name\":\"John\",\"email\":\"john@example.com\"}") UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
