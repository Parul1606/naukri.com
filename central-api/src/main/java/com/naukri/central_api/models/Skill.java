package com.naukri.central_api.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Skill {
    UUID id;
    String name;
}
