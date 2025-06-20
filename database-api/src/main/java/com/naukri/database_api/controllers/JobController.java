package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Job;
import com.naukri.database_api.repositories.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/job")
public class JobController {

    JobRepo jobRepo;

    @Autowired
    public JobController(JobRepo jobRepo){
        this.jobRepo = jobRepo;
    }

    @PostMapping("/save")
    public ResponseEntity<Job> create(@RequestBody Job job){
        jobRepo.save(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable UUID id){
        Job job = jobRepo.findById(id).orElse(null);
        return new ResponseEntity<>(job,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Job>> findAll(){
        List<Job> jobs = jobRepo.findAll();
        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Job job){
        jobRepo.save(job);
        return new ResponseEntity(job,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@PathVariable UUID id){
        jobRepo.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
