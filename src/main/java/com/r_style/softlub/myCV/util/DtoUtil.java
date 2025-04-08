package com.r_style.softlub.myCV.util;

import com.r_style.softlub.myCV.dto.SkillDto;
import com.r_style.softlub.myCV.dto.UserDto;
import com.r_style.softlub.myCV.model.Skill;
import com.r_style.softlub.myCV.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DtoUtil {
    private final ModelMapper modelMapper;

    public UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User toUserEntity(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    public SkillDto toSkillDto(Skill skill) {
        return modelMapper.map(skill, SkillDto.class);
    }

    public Skill toSkillEntity(SkillDto dto) {
        return modelMapper.map(dto, Skill.class);
    }
}
