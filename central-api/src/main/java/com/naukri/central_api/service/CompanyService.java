package com.naukri.central_api.service;

import com.naukri.central_api.connectors.DatabaseApiConnector;
import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Company;
import com.naukri.central_api.utility.MappingUtility;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    MappingUtility mappingUtility;
    DatabaseApiConnector dbApiConnector;
    UserService userService;

    @Autowired
    public CompanyService(MappingUtility mappingUtility,
                          DatabaseApiConnector dbApiConnector,
                          UserService userService){
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
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
}
