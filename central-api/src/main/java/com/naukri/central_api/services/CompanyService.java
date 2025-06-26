package com.naukri.central_api.services;

import com.naukri.central_api.connectors.DatabaseApiConnector;
import com.naukri.central_api.connectors.NotificationApiConnector;
import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.dto.CreateJobDto;
import com.naukri.central_api.dto.RecruiterDetailsDto;
import com.naukri.central_api.exceptions.UnAuthorizedException;
import com.naukri.central_api.models.*;
import com.naukri.central_api.utility.AuthUtility;
import com.naukri.central_api.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    MappingUtility mappingUtility;
    DatabaseApiConnector dbApiConnector;
    UserService userService;
    NotificationApiConnector notificationApiConnector;
    AuthUtility authUtility;
    ApplicationFormService applicationFormService;
    SkillService skillService;
    JobService jobService;

    @Autowired
    public CompanyService(MappingUtility mappingUtility,
                          DatabaseApiConnector dbApiConnector,
                          UserService userService,
                          NotificationApiConnector notificationApiConnector,
                          AuthUtility authUtility,
                          ApplicationFormService applicationFormService,
                          SkillService skillService,
                          JobService jobService){
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
        this.notificationApiConnector = notificationApiConnector;
        this.authUtility = authUtility;
        this.applicationFormService = applicationFormService;
        this.skillService = skillService;
        this.jobService = jobService;
    }
    /**
    * Expectation of this function is to save company details in the company table
    * to save company details it will be calling database api connector which will be hitting request to
    * database api company controller save endpoint
    * @return
    */

    public Company registerCompany(CompanyRegistrationDto companyRegistrationDto){
        // now we need to map companyregistrationdto details to company class.
        Company company = mappingUtility.mapCompanyDtoToCompanyModel(companyRegistrationDto);
        // now we need to save this company model inside our database.
        company = this.saveCompany(company);
        //returning company object from here.
        // now we should create admin account for the company.
        AppUser admin = mappingUtility.mapCompanyDtoToAdmin(companyRegistrationDto, company);
        // Now we need to save this admin
        userService.saveUser(admin);
        return company;
    }

    /**
     * This save method will internally call database api connector which will be calling database api save company endpoint
     * @return company
     */
    public Company saveCompany(Company company){
        // database api connector to save company details in the company table
        return dbApiConnector.callSaveCompanyEndpoint(company);

    }

    public AppUser inviteRecruiter(RecruiterDetailsDto recruiterDetailsDto,
                                   String Authorization){
        String token = Authorization.substring(7);
        AppUser admin = userService.getUserFromToken(token);
        if(!userService.isAdminUser(admin)){
            throw new UnAuthorizedException("Invalid Operation");
        }
        Company company = admin.getCompany();
        // we need create user object for the recruiter
        AppUser recruiter = mappingUtility.mapRecruiterDtoToAppUser(recruiterDetailsDto, company);
        userService.saveUser(recruiter);
        token = authUtility.generateJwtToken(recruiter.getEmail(), recruiter.getPassword(), "RECRUITER");

        // Mail Logic
        // we need to write some logic such that we will be able to notify recruiter that hey you are invited to this company.
        // from here we need to trigger Notification-api -> invite-recruiter endpoint such that recruiter will receieve mail
        notificationApiConnector.callInviteRecruiterEndpoint(recruiter, token);
        return recruiter;
    }

    public AppUser acceptInvitation(String token){
        String[] payload = userService.decryptJwtToken(token).split(":");
        String email = payload[0];
        String password = payload[1];
        String role = payload[2];
        if(!userService.validateCredentials(email, password)){
            throw new UnAuthorizedException("Invalid exception");
        }
        AppUser recruiter = userService.getUserFromToken(token);
        if(!userService.isUserRecruiter(recruiter)){
            throw new UnAuthorizedException("Invalid operation");
        }
        recruiter.setStatus("ACTIVE");
        userService.saveUser(recruiter);
        //mail to company admin that hey this recruiter has accepted ur invitation
        String adminEmail = recruiter.getCompany().getCompanyName();
        AppUser admin = userService.getUserByEmail(adminEmail);
        List<AppUser> mailDetails = new ArrayList<>();
        mailDetails.add(recruiter);
        mailDetails.add(admin);
        //calling notification api connector
        notificationApiConnector.callAcceptInvitationEndpoint(mailDetails);
        return recruiter;
    }

    public Job createJob(CreateJobDto createJobDto,
                          String Authorization){
        String token = authUtility.extractTokenFromBearerToken(Authorization);
        AppUser recruiter = userService.getUserFromToken(token);
        if(!userService.isUserRecruiter(recruiter)){
            throw new UnAuthorizedException("Not Authorized to create jobs");
        }
        // we need to map createjobdto to job model
        // so, to map details of createjob to job we need to think about -> what are the necessary details to required to create job model object
        // as job model is dependent on application form object. we need to think about hoq we can create application form
        ApplicationForm applicationForm = applicationFormService.createApplicationFormByQuestions(createJobDto.getQuestions());
        List<Skill> skills = skillService.getAllSkills(createJobDto.getSkills());
        Job job =  mappingUtility.createJobFromJobDto(createJobDto, applicationForm, skills, recruiter);
        // we need to save this job
        return jobService.saveJob(job);
    }
}
