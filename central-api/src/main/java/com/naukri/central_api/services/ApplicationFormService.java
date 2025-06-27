package com.naukri.central_api.services;

import com.naukri.central_api.connectors.DatabaseApiConnector;
import com.naukri.central_api.models.ApplicationForm;
import com.naukri.central_api.models.Questions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationFormService {

    QuestionService questionService;
    DatabaseApiConnector databaseApiConnector;

    public ApplicationFormService(QuestionService questionService,
                                  DatabaseApiConnector databaseApiConnector){
        this.questionService = questionService;
        this.databaseApiConnector = databaseApiConnector;
    }

    public ApplicationForm createApplicationFormByQuestions(List<String> questionList){
        // we are getting list of questions in strings
        // but to create applicationForm we require list of question objects
        // we need to get all questions from service.
        List<Questions> questions = questionService.getAllQuestions(questionList);
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setQuestionsList(questions);
        return applicationForm;
    }

    public ApplicationForm saveApplicationForm(ApplicationForm applicationForm){
        // we need o call dbapi to save the application form
        return databaseApiConnector.callSaveApplicationForm(applicationForm);
    }
}
