package com.naukri.database_api.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * getter, setter, allargscosntructor, noargsconstructor and toString
 * all these are generated automatically with the help of HIBERNATE using lombok library
 * */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // this is nothing but default constructor
@ToString

@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // this line indicates that hibernate will generate id for us
    UUID id;
    String name;

    @Column(name = "user_email", unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    String status;

    @Column(unique = true, nullable = false)
    Long phoneNumber;

    String userType;

    @ManyToOne
    Company company;

    @ManyToMany
    List<Skill> skillSet;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
