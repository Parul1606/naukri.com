package com.naukri.central_api.controller;

import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.dto.CreateJobDto;
import com.naukri.central_api.dto.JwtTokenResponseDto;
import com.naukri.central_api.dto.RecruiterDetailsDto;
import com.naukri.central_api.exceptions.UnAuthorizedException;
import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Company;
import com.naukri.central_api.models.Job;
import com.naukri.central_api.services.CompanyService;
import com.naukri.central_api.services.JobService;
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
    JobService jobService;

    @Autowired
    public CompanyController(CompanyService companyService,
                             AuthUtility authUtility,
                             JobService jobService){
        this.companyService = companyService;
        this.authUtility = authUtility;
        this.jobService = jobService;
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
        AppUser recruiter = companyService.inviteRecruiter(recruiterDetailsDto, Authorization);
        return new ResponseEntity(recruiter, HttpStatus.CREATED);
    }

    @GetMapping("/accept-invitation/{token}")
    public ResponseEntity acceptInvitation(@PathVariable String token){
        // call company service
        try{
            AppUser recruiter = companyService.acceptInvitation(token);
            return new ResponseEntity(recruiter, HttpStatus.CREATED);
        } catch (UnAuthorizedException e){
            return new ResponseEntity(e, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/job/create")
    public ResponseEntity createJob(@RequestBody CreateJobDto createJobDto,
                                    @RequestHeader String Authorization){
        try{
            Job job = companyService.createJob(createJobDto, Authorization);
            return new ResponseEntity(job, HttpStatus.CREATED);
        } catch(UnAuthorizedException e){
            return new ResponseEntity(e, HttpStatus.UNAUTHORIZED);
        }
    }
}
