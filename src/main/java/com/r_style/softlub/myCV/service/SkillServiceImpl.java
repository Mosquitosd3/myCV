package com.r_style.softlub.myCV.service;

import com.r_style.softlub.myCV.dto.SkillDto;
import com.r_style.softlub.myCV.exception.NotFoundException;
import com.r_style.softlub.myCV.model.Skill;
import com.r_style.softlub.myCV.model.User;
import com.r_style.softlub.myCV.repository.SkillRepository;
import com.r_style.softlub.myCV.repository.UserRepository;
import com.r_style.softlub.myCV.util.DtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService{
    private final SkillRepository skillRepo;
    private final UserRepository userRepo;
    private final DtoUtil dtoUtil;

    @Override
    public List<SkillDto> getAllSkills() {
        return skillRepo.findAll().stream()
                .map(dtoUtil::toSkillDto)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDto getSkillById(Long id) {
        Skill skill = skillRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill not found: id = " + id));
        return dtoUtil.toSkillDto(skill);
    }

    @Override
    public SkillDto saveSkill(SkillDto skillDto, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Skill skill = dtoUtil.toSkillEntity(skillDto);
        skill.setUser(user);
        skill = skillRepo.save(skill);
        return dtoUtil.toSkillDto(skill);
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepo.deleteById(id);
    }
}
