package com.naukri.central_api.connectors;

import com.naukri.central_api.models.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class NotificationApiConnector extends RestAPI {

    @Value("${notification.api.baseurl}")
    String baseUrl;

    /**
     * when this method will get triggered so we will be calling invite recruiter endpoint of notification api
     * @param recruiter
     * */
    public void callInviteRecruiterEndpoint(AppUser recruiter){
        String url = baseUrl + "/company/invite-recruiter";
        this.makePutCall(url, recruiter, new HashMap<>());
    }
}
