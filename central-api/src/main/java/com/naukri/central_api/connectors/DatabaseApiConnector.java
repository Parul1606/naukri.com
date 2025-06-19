package com.naukri.central_api.connectors;

import com.naukri.central_api.models.AppUser;
import com.naukri.central_api.models.Skill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DatabaseApiConnector {

    // we will write all calling methods here we will calling all the database api endpoints from this class

    @Value("${database.api.baseurl}")
    String baseUrl;

    public Skill callGetSkillByNameEndpoint(String skillName){
        // In this function we will have logic to hit getSkillByName endpoint of DBAPI
        // Create URL
        String url = baseUrl + "/skill/get/" + skillName;
        // creation of request
        RequestEntity request = RequestEntity.get(url).build();
        // Use restTemplate class to hit the api url
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Skill> response = restTemplate.exchange(url, HttpMethod.GET, request, Skill.class);
        return response.getBody();
    }

    public AppUser callSaveUserEndpoint(AppUser user){
        String url = baseUrl + "/user/save";
        RequestEntity request = RequestEntity.post(url).body(user);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);
        return response.getBody();

    }

    public Skill callSaveSkillEndpoint(Skill skill){
        String url = baseUrl + "skill/save";
        RequestEntity request = RequestEntity.post(url).body(skill);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Skill> response = restTemplate.exchange(url, HttpMethod.POST, request, Skill.class);
        return response.getBody();
    }
}
