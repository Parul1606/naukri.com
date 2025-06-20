package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Answer;
import com.naukri.database_api.repositories.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/answer")
public class AnswerController {

    AnswerRepo answerRepo;

    @Autowired
    public AnswerController(AnswerRepo answerrepo){
        this.answerRepo = answerrepo;
    }

    @PostMapping("/save")
    public ResponseEntity<Answer> create(@RequestBody Answer answer){
        answerRepo.save(answer);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> findById(@PathVariable UUID id){
        Answer answer = answerRepo.findById(id).orElse(null);
        return new ResponseEntity<>(answer,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Answer>> findAll(){
        List<Answer> answers = answerRepo.findAll();
        return new ResponseEntity<>(answers,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Answer answer){
        answerRepo.save(answer);
        return new ResponseEntity(answer, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@PathVariable UUID id){
        answerRepo.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
