package com.naukri.notification_api.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Skill {

    UUID id;
    String name;
}
