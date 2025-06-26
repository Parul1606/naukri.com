package com.naukri.central_api.services;

import com.naukri.central_api.connectors.DatabaseApiConnector;
import com.naukri.central_api.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    DatabaseApiConnector databaseApiConnector;

    @Autowired
    public JobService(DatabaseApiConnector databaseApiConnector){
        this.databaseApiConnector = databaseApiConnector;
    }
    public Job saveJob(Job job){
        return databaseApiConnector.callSaveJobEndpoint(job);
    }
}
