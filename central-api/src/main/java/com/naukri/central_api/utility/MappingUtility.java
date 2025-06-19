package com.naukri.central_api.utility;

import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Skill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  //with the help of component annotation spring gets to know we need to create object of this class as well
public class MappingUtility {

    public AppUser mapJobSeekerDetailsToAppUser(JobSeekerRegistrationDto jobSeekerDto, List<Skill> skills){
        AppUser appUser = new AppUser();
        appUser.setUserType("JOBSEEKER");
        appUser.setName(jobSeekerDto.getName());
        appUser.setEmail(jobSeekerDto.getEmail());
        appUser.setPassword(jobSeekerDto.getPassword());
        appUser.setPhoneNumber(jobSeekerDto.getPhoneNumber());
        //appUser.setSkillSet(); for now we are not able to set List<Skill> to appUser
        // reason is from jobSeekerDto we are getting List<String> -> List<Skill>
        appUser.setSkillSet(skills);
        return appUser;
    }
}
