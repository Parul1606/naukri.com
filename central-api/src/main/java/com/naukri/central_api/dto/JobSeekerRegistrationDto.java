package com.naukri.central_api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobSeekerRegistrationDto {
    private String name;
    private String password;
    private String email;
    private Long phoneNumber;
    private List<String> skillSet;
}
