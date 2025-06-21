package com.naukri.central_api.service;

import com.naukri.central_api.connectors.DatabaseApiConnector;
import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Skill;
import com.naukri.central_api.service.SkillService;
import com.naukri.central_api.utility.MappingUtility;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    SkillService skillService;
    MappingUtility mappingUtility;
    DatabaseApiConnector dbApiConnector;

    @Autowired
    public UserService(SkillService skillService,
                       MappingUtility mappingUtility,
                       DatabaseApiConnector dbApiConnector){
        this.skillService = skillService;
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
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
        //List<Skill> skills = new ArrayList<>();
        AppUser jobSeeker = mappingUtility.mapJobSeekerDetailsToAppUser(jobSeekerDto, skills);
        AppUser user = this.saveUser(jobSeeker);
        return user;

    }

    public boolean validateCredentials(String email, String password){
        // we need to call databaseapi to provide user object on the basis of email.
        // so to call the database api we should call database api connector class
        AppUser user = dbApiConnector.callGetUserByEmailEndpoint(email);
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    AppUser saveUser(AppUser user){  // by not declaring public this class we are making it default - that means it won't be accessible outside this package
        // this method will be having logic to call SaveUser endpoint of appuser control of dbApi
        return dbApiConnector.callSaveUserEndpoint(user);
    }
}
