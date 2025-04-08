package com.r_style.softlub.myCV.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String bio;
    private String photoUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Skill> skills = new ArrayList<>();
}

