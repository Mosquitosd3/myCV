package com.r_style.softlub.myCV.controller;

import com.r_style.softlub.myCV.dto.SkillDto;
import com.r_style.softlub.myCV.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @GetMapping
    public List<SkillDto> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public SkillDto getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PostMapping("/user/{userId}/save")
    public SkillDto createSkill(@PathVariable Long userId, @RequestBody SkillDto skillDto) {
        return skillService.saveSkill(skillDto, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}
