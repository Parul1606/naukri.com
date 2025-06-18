package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Skill;
import com.naukri.database_api.repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/skill")
public class SkillController {

    SkillRepo skillRepo;

    @Autowired
    public SkillController(SkillRepo skillRepo){
        this.skillRepo = skillRepo;
    }

    @PostMapping("/save/answer")
    public ResponseEntity<Skill> create(@RequestBody Skill skills){
        skillRepo.save(skills);
        return new ResponseEntity<>(skills, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> findById(@PathVariable UUID id){
        Skill skills = skillRepo.findById(id).orElse(null);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Skill>> findAll(){
        List<Skill> skills = skillRepo.findAll();
        return new ResponseEntity<>(skills,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Skill skills){
        skillRepo.save(skills);
        return new ResponseEntity(skills,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@PathVariable UUID id){
        skillRepo.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
