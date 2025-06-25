package com.naukri.central_api.connectors;

import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Company;
import com.naukri.central_api.models.Skill;
import com.naukri.central_api.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class DatabaseApiConnector extends RestAPI{
    // we will write all calling methods here we will calling all the database api endpoints from this class

    @Value("${database.api.baseurl}")
    String baseUrl;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * this function will make request to the database api get user by email endpoint
     * @param email
     * @return
     */
    public AppUser callGetUserByEmailEndpoint(String email){
        String endpoint = baseUrl + "/user/email/" + email;
        Object resp = this.makeGetCall(endpoint, new HashMap<>());
        if(resp == null){
            return null;
        }
        return modelMapper.map(resp, AppUser.class);
    }

    public Skill callGetSkillByNameEndpoint(String skillName){
        // In this function we will have logic to hit getSkillByName endpoint of DBAPI
        // Create URL
        String url = baseUrl + "/skill/get/" + skillName;
        Object resp = this.makeGetCall(url, new HashMap<>());
        if(resp == null){
            return null;
        }
        return modelMapper.map(resp, Skill.class);
    }

    public AppUser callSaveUserEndpoint(AppUser user){
        String url = baseUrl + "/user/save";
        Object resp = this.makePostCall(url, user, new HashMap<>());
        if(resp == null){
            return null;
        }
        return modelMapper.map(resp, AppUser.class);
    }

    public Skill callSaveSkillEndpoint(Skill skill){
        String url = baseUrl + "/skill/save";
        RequestEntity request = RequestEntity.post(url).body(skill);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Skill> response = restTemplate.exchange(url, HttpMethod.POST, request, Skill.class);
        return response.getBody();
    }


    public Company callSaveCompanyEndpoint(Company company){
        String url = baseUrl + "/company/save";
        RequestEntity request = RequestEntity.post(url).body(company);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Company> response = restTemplate.exchange(url, HttpMethod.POST, request, Company.class);
        return response.getBody();
    }

}
