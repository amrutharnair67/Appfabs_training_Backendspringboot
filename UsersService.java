package com.example.JavaTournament.service;

import com.example.JavaTournament.entity.Users;
import com.example.JavaTournament.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Method to check if a user exists by email
    public boolean existsByEmail(String email) {
        return usersRepository.findByEmail(email) != null;
    }

    // Method to generate a reset password token and send an email
    public String generateResetPasswordToken(String email) {
        Users user = usersRepository.findByEmail(email);
        if (user != null) {
            try {
                String token = UUID.randomUUID().toString(); // Generate a unique token
                user.setResetPasswordToken(token);
                usersRepository.save(user); // Save the user with the new token

                // Send the token to the user's email
                sendResetPasswordEmail(user.getEmail(), token);
                return token; // Return the token on success
            } catch (Exception e) {
                // Log the error for debugging
                System.err.println("Error generating reset token: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging
                return null; // Return null to indicate failure
            }
        }
        return null; // Return null if the user does not exist
    }



    // Send the reset password email using SMTP
    private void sendResetPasswordEmail(String email, String token) {
        String subject = "Password Reset Request";
        String body = "Here is your password reset token: " + token + "\n\nUse this token to reset your password.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("your-email@gmail.com"); // Replace with your email

        mailSender.send(message);
        System.out.println("Reset password email sent successfully to " + email);
    }

    public boolean resetPassword(String token, String newPassword) {
        System.out.println("Resetting password for token: " + token); // Debug statement
        Users user = usersRepository.findByResetPasswordToken(token);
        if (user != null) {
            System.out.println("User found for token: " + user.getEmail()); // Debug statement
            user.setPassword(newPassword);
            user.setResetPasswordToken(null); // Clear the reset token after password is reset
            usersRepository.save(user);
            return true;
        }
        System.out.println("No user found for the token."); // Debug statement
        return false;
    }



    // Additional methods
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public void saveUser(Users user) {
        usersRepository.save(user);
    }
}
