package com.r_style.softlub.myCV.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyKeyJavaClass;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SkillDto {
    private Long id;
    private String name;
    private String level;
}
