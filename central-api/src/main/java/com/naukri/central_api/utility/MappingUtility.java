package com.naukri.central_api.utility;

import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.dto.RecruiterDetailsDto;
import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Company;
import com.naukri.central_api.models.Skill;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  //with the help of component annotation spring gets to know we need to create object of this class as well
public class MappingUtility {

    public AppUser mapJobSeekerDetailsToAppUser(JobSeekerRegistrationDto jobSeekerDto, List<Skill> skills){
        AppUser appUser = new AppUser();
        appUser.setUserType("JOB_SEEKER");
        appUser.setName(jobSeekerDto.getName());
        appUser.setEmail(jobSeekerDto.getEmail());
        appUser.setPassword(jobSeekerDto.getPassword());
        appUser.setPhoneNumber(jobSeekerDto.getPhoneNumber());
        //appUser.setSkillSet(); for now we are not able to set List<Skill> to appUser
        // reason is from jobSeekerDto we are getting List<String> -> List<Skill>
        appUser.setSkillSet(skills);
        return appUser;
    }

    public Company mapCompanyDtoToCompanyModel(CompanyRegistrationDto companyRegistrationDto){
        Company company = new Company();
        company.setCompanyName(companyRegistrationDto.getCompanyName());
        company.setCompanySize(companyRegistrationDto.getCompanySize());
        company.setIndustry(companyRegistrationDto.getEmail());
        company.setEmail(companyRegistrationDto.getEmail());
        company.setWebsiteLink(companyRegistrationDto.getWebsiteLink());
        company.setLinkedinLink(companyRegistrationDto.getLinkedinLink());
        return company;
    }

    public AppUser mapCompanyDtoToAdmin(CompanyRegistrationDto companyRegistrationDto, Company company){
        AppUser admin = new AppUser();
        admin.setCompany(company);
        admin.setName("Admin");
        admin.setPassword(companyRegistrationDto.getPassword());
        admin.setEmail(companyRegistrationDto.getEmail());
        admin.setUserType("ADMIN");
        admin.setPhoneNumber(companyRegistrationDto.getPhoneNumber());
        return admin;
    }

    public AppUser mapRecruiterDtoToAppUser(RecruiterDetailsDto recruiterDetailsDto,
                                            Company company){
        AppUser user = new AppUser();
        user.setName(recruiterDetailsDto.getName());
        user.setEmail(recruiterDetailsDto.getEmail());
        user.setPhoneNumber(recruiterDetailsDto.getPhonNumber());
        user.setUserType("RECRUITER");
        user.setPassword("DefaultPass123");
        user.setCompany(company);
        user.setStatus("INACTIVE");
        return user;
    }
}
