package com.naukri.central_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyRegistrationDto {

    String companyName;
    String email;
    String websiteLink;
    String linkedinLink;
    String password;
    Long phoneNumber;
    int companySize;
    String industry;
}
