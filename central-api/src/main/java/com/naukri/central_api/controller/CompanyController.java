package com.naukri.central_api.controller;

import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.dto.JwtTokenResponseDto;
import com.naukri.central_api.dto.RecruiterDetailsDto;
import com.naukri.central_api.models.Company;
import com.naukri.central_api.service.CompanyService;
import com.naukri.central_api.utility.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/central/company")
public class CompanyController {
    CompanyService companyService;
    AuthUtility authUtility;

    @Autowired
    public CompanyController(CompanyService companyService,
                             AuthUtility authUtility){
        this.companyService = companyService;
        this.authUtility = authUtility;
    }

    @PostMapping("/register")
    public ResponseEntity registerCompany(@RequestBody CompanyRegistrationDto companyRegistrationDto){

        // companyservice -> to save company details to the database.
        Company company = companyService.registerCompany(companyRegistrationDto);
        String token = authUtility.generateJwtToken(companyRegistrationDto.getEmail(),
                companyRegistrationDto.getPassword(),
                "ADMIN");
        JwtTokenResponseDto tokenResp = new JwtTokenResponseDto(token);
        return new ResponseEntity<>(tokenResp, HttpStatus.CREATED);
    }

    @PostMapping("/invite-recruiter")
    public ResponseEntity inviteRecruiter(@RequestBody RecruiterDetailsDto recruiterDetailsDto,
                                          @RequestHeader String Authorization){

    }
}
