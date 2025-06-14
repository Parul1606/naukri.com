package com.naukri.database_api.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;

    @Column(name = "user_email", unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    Long phoneNumber;

    String userType;

    @ManyToOne
    Company company;

    @CreationTimestamp
    LocalDateTime createdAt;

    @CreationTimestamp
    LocalDateTime updatedAt;
}
