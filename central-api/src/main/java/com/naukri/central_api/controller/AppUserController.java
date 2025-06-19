package com.naukri.central_api.controller;

import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/central/user")
public class AppUserController {

    @PostMapping("/register")
    public void registerJobApplicant(@RequestBody JobSeekerRegistrationDto jobSeekerDto){

    }
}

