package com.naukri.central_api.services;

import com.naukri.central_api.models.ApplicationForm;
import com.naukri.central_api.models.Questions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationFormService {

    QuestionService questionService;

    public ApplicationFormService(QuestionService questionService){
        this.questionService = questionService;
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
}
