package com.naukri.database_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @OneToMany
    List<Answer> answers;
}
