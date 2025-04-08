package com.r_style.softlub.myCV.repository;

import com.r_style.softlub.myCV.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
