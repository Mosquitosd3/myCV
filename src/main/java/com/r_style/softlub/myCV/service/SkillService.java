package com.r_style.softlub.myCV.service;

import com.r_style.softlub.myCV.dto.SkillDto;

import java.util.List;

public interface SkillService {
    List<SkillDto> getAllSkills();
    SkillDto getSkillById(Long id);
    SkillDto saveSkill(SkillDto skillDto, Long userId);
    void deleteSkill(Long id);
}
