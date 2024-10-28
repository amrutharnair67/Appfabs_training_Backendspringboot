package com.example.JavaTournament.repository;

import com.example.JavaTournament.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByResetPasswordToken(String resetPasswordToken); // Ensure correct field name usage
}
