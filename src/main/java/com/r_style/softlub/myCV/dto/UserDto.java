package com.r_style.softlub.myCV.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "User data transfer object")
public class UserDto {
    @Schema(example = "1", description = "Unique identifier")
    private Long id;
    @NotBlank(message = "Name is required")
    @Schema(example = "John Doe", required = true)
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    @Size(max = 500, message = "Bio too long")
    private String bio;
    private String photoUrl;
    private List<SkillDto> skills;
}
