package com.naukri.central_api.services;

import com.naukri.central_api.connectors.DatabaseApiConnector;
import com.naukri.central_api.models.Questions;
import com.naukri.central_api.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    DatabaseApiConnector dbApiConnector;
    MappingUtility mappingUtility;

    @Autowired
    public QuestionService(DatabaseApiConnector dbApiConnector,
                           MappingUtility mappingUtility){
        this.dbApiConnector = dbApiConnector;
        this.mappingUtility = mappingUtility;
    }

    public List<Questions> getAllQuestions(List<String> questionList){
        List<Questions> questions = new ArrayList<>();
        for(String question : questionList){
            Questions q = mappingUtility.createQuestionFromQuestionName(question, true);
            q = this.saveQuestions(q);
            questions.add(q);
        }
        return questions;
    }

    /**
     * Work of this function is to save the question inside the database
     * @param questions
     * @return
     * */
    public Questions saveQuestions(Questions questions){
        return dbApiConnector.callCreateQuestionEndpoint(questions);
    }

}
