package com.example.JavaTournament.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users") // Table name in the database
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-incrementing IDs
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Team name is required")
    private String teamName;

    @Column(nullable = false)
    @NotBlank(message = "Captain's first name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Captain first name must contain only letters.")

    private String captainFirstName;

    @Column(nullable = false)
    @NotBlank(message = "Captain's last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Captain last name must contain only letters.")

    private String captainLastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one special character.")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Column(nullable = false)
    @NotBlank(message = "Player 2's first name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = " Player 2's first name must contain only letters.")

    private String player2FirstName;

    @Column(nullable = false)
    @NotBlank(message = "Player 2's last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Player 2's last  name must contain only letters.")

    private String player2LastName;

    @Column(nullable = false)
    @NotBlank(message = "Player 3's first name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Player 3's first name must contain only letters.")

    private String player3FirstName;

    @Column(nullable = false)
    @NotBlank(message = "Player 3's last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Player 3's last name must contain only letters.")

    private String player3LastName;

    @Column(nullable = false)
    @NotBlank(message = "Substitute 1's first name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Substitute 1's first name  must contain only letters.")

    private String substitute1FirstName;

    @Column(nullable = false)
    @NotBlank(message = "Substitute 1's last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Substitute 1's last name must contain only letters.")

    private String substitute1LastName;

    @Column(nullable = true)
    private String username;

    @Column(nullable = true)
    private String teamLogoPath;

    @Lob

    private String comment;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;
}
