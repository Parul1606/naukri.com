package com.naukri.central_api.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Questions {
    UUID id;

    String question;

    boolean isMandatory;
}
