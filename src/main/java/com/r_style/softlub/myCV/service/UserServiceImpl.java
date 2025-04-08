package com.r_style.softlub.myCV.service;

import com.r_style.softlub.myCV.dto.UserDto;
import com.r_style.softlub.myCV.exception.NotFoundException;
import com.r_style.softlub.myCV.model.User;
import com.r_style.softlub.myCV.repository.UserRepository;
import com.r_style.softlub.myCV.util.DtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepo;
    private final DtoUtil dtoUtil;

    @Cacheable(value = "users", key = "{#page, #size}")
    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable)
                .map(dtoUtil::toUserDto);
    }

    @Cacheable(value = "users", key = "#id")
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: id = "  + id));
        return dtoUtil.toUserDto(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = dtoUtil.toUserEntity(userDto);
        if (user.getSkills() != null) {
            user.getSkills().forEach(skill -> skill.setUser(user));
        }
        return dtoUtil.toUserDto(userRepo.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
