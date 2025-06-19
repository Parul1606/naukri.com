package com.naukri.central_api.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Company {
    UUID id;
    String companyName;
    String email;
    String websiteLink;
    String LinkedinLink;
    int companySize;
    String industry;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
