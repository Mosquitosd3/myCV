package com.r_style.softlub.myCV.service;

import com.r_style.softlub.myCV.dto.SkillDto;
import com.r_style.softlub.myCV.model.Level;
import com.r_style.softlub.myCV.model.Skill;
import com.r_style.softlub.myCV.model.User;
import com.r_style.softlub.myCV.repository.SkillRepository;
import com.r_style.softlub.myCV.repository.UserRepository;
import com.r_style.softlub.myCV.util.DtoUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private DtoUtil dtoUtil;

    @InjectMocks
    private SkillServiceImpl skillService;

    @Test
    void getAllSkills_ReturnsSkillList() {
        Skill skill = new Skill(1L, "Java", Level.EXPERT, null);
        SkillDto skillDto = new SkillDto(1L, "Java", "EXPERT");

        when(skillRepo.findAll()).thenReturn(List.of(skill));
        when(dtoUtil.toSkillDto(skill)).thenReturn(skillDto);

        List<SkillDto> result = skillService.getAllSkills();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(skillDto);
    }

    @Test
    void getSkillById_ReturnsSkillDto() {
        Long skillId = 1L;
        Skill skill = new Skill(skillId, "Java", Level.EXPERT, null);
        SkillDto skillDto = new SkillDto(skillId, "Java", "EXPERT");

        when(skillRepo.findById(skillId)).thenReturn(Optional.of(skill));
        when(dtoUtil.toSkillDto(skill)).thenReturn(skillDto);

        SkillDto result = skillService.getSkillById(skillId);

        assertThat(result).isEqualTo(skillDto);
    }

    @Test
    void saveSkill_WithValidUserId_ReturnsSavedSkill() {
        // Arrange
        Long userId = 1L;
        User user = new User(userId, "John", "john@test.com", null, null, null);
        SkillDto inputDto = new SkillDto(null, "Java", "EXPERT");
        Skill savedSkill = new Skill(1L, "Java", Level.EXPERT, user);
        SkillDto expectedDto = new SkillDto(1L, "Java", "EXPERT");

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(dtoUtil.toSkillEntity(inputDto)).thenReturn(new Skill(null, "Java", Level.EXPERT, user));
        when(skillRepo.save(any(Skill.class))).thenReturn(savedSkill);
        when(dtoUtil.toSkillDto(savedSkill)).thenReturn(expectedDto);

        SkillDto result = skillService.saveSkill(inputDto, userId);

        assertThat(result).isEqualTo(expectedDto);
        assertThat(savedSkill.getUser()).isEqualTo(user);
    }

    @Test
    void deleteSkill_ExecutesWithoutErrors() {
        // Arrange
        Long skillId = 1L;
        doNothing().when(skillRepo).deleteById(skillId);

        // Act & Assert
        assertThatNoException()
                .isThrownBy(() -> skillService.deleteSkill(skillId));
    }
}