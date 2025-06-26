package com.naukri.central_api.dto;

import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Skill;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateJobDto {

    String state;
    String shortDescription;
    String location;
    String jobDescription;
    List<String> skills;
    List<String> questions;
}
