package com.naukri.central_api.service;

import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Skill;
import com.naukri.central_api.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    SkillService skillService;
    MappingUtility mappingUtility;

    @Autowired
    public UserService(SkillService skillService,
                       MappingUtility mappingUtility){
        this.skillService = skillService;
        this.mappingUtility = mappingUtility;
    }

    public AppUser registerJobSeeker(JobSeekerRegistrationDto jobSeekerDto){
        // Frontend -> Controller -> Service
        // central api needs to use database api to save jobseeker
        // from our central api we need to think something such that we will be able to hit
        // database-api user registration endpoint.

        // 1. we need to map data of jobSeekerDto -> AppUser Model.
        // opt1. write mapping logic here itself
        // opt2. write mapping logic in different class and call the mapping method of that class from helper functions

        // we identified oneProblem that problem is to register user we need List<Skill>
        // we are having List<String> we need to fetch all the Skill Object from the database api

        // we need to fetch List<Skill> API for List<String>
        List<String> skillNames = jobSeekerDto.getSkillSet();
        List<Skill> skills = skillService.getAllSkills(skillNames);
        AppUser jobSeeker = mappingUtility.mapJobSeekerDetailsToAppUser(jobSeekerDto, skills);
        AppUser user = this.saveUser(jobSeeker);
        return user;

    }

    public AppUser saveUser(AppUser user){
        // this method will be having logic to call SaveUser endpoint of appuser control of dbApi
    }
}
