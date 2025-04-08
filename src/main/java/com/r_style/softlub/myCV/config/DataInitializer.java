package com.r_style.softlub.myCV.config;

import com.r_style.softlub.myCV.model.Level;
import com.r_style.softlub.myCV.model.Skill;
import com.r_style.softlub.myCV.model.User;
import com.r_style.softlub.myCV.repository.SkillRepository;
import com.r_style.softlub.myCV.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SkillRepository skillRepo;

    @Override
    public void run(String... args) {
        User user = new User();
        user.setName("Коля");
        user.setEmail("kolya@example.com");
        user.setBio("Java-разработчик");
        user.setPhotoUrl("https://example.com/photo.jpg");
        userRepo.save(user);

        Skill java = new Skill();
        java.setName("Java");
        java.setLevel(Level.EXPERT);
        java.setUser(user);
        skillRepo.save(java);

        Skill spring = new Skill();
        spring.setName("Spring");
        spring.setLevel(Level.EXPERT);
        spring.setUser(user);
        skillRepo.save(spring);
    }
}