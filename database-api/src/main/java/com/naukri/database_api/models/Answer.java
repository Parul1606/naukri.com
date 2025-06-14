package com.naukri.database_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Answer {

    @Id
    UUID id;

    String answer;

    @ManyToOne
    Questions question;
}
