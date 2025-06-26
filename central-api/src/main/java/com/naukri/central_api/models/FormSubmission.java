package com.naukri.central_api.models;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormSubmission {
    UUID id;
    List<Answer> answers;
}
