package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Questions;
import com.naukri.database_api.repositories.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/questions")
public class QuestionsController {

    QuestionsRepo questionsRepo;

    @Autowired
    public QuestionsController(QuestionsRepo questionsRepo){
        this.questionsRepo = questionsRepo;
    }

    @PostMapping("/save")
    public ResponseEntity<Questions> create(@RequestBody Questions questions){
        questionsRepo.save(questions);
        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questions> findById(@PathVariable UUID id){
        Questions questions = questionsRepo.findById(id).orElse(null);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Questions>> findAll(){
        List<Questions> questions = questionsRepo.findAll();
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Questions questions){
        questionsRepo.save(questions);
        return new ResponseEntity(questions,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@PathVariable UUID id){
        questionsRepo.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
