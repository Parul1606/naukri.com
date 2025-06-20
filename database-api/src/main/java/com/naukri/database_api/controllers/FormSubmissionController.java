package com.naukri.database_api.controllers;

import com.naukri.database_api.models.FormSubmission;
import com.naukri.database_api.repositories.FormSubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/submission")
public class FormSubmissionController {

    FormSubmissionRepo formSubmissionRepo;

    @Autowired
    public FormSubmissionController(FormSubmissionRepo formSubmissionRepo){
        this.formSubmissionRepo = formSubmissionRepo;
    }

    @PostMapping("/save")
    public ResponseEntity<FormSubmission> create(@RequestBody FormSubmission formSubmission){
        formSubmissionRepo.save(formSubmission);
        return new ResponseEntity<>(formSubmission,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormSubmission> findById(@PathVariable UUID id){
        FormSubmission formSubmission = formSubmissionRepo.findById(id).orElse(null);
        return new ResponseEntity<>(formSubmission,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<FormSubmission>> findAll(){
        List<FormSubmission> formSubmissions = formSubmissionRepo.findAll();
        return new ResponseEntity<>(formSubmissions,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<FormSubmission> update(@RequestBody FormSubmission formSubmission){
        formSubmissionRepo.save(formSubmission);
        return new ResponseEntity<>(formSubmission,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        formSubmissionRepo.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
