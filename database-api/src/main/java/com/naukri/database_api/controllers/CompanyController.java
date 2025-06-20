package com.naukri.database_api.controllers;

import com.naukri.database_api.models.Company;
import com.naukri.database_api.repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/company")
public class CompanyController {

    CompanyRepo companyRepo;

    @Autowired
    public CompanyController(CompanyRepo companyRepo){
        this.companyRepo = companyRepo;
    }

    @PostMapping("/save")
    public ResponseEntity createCompany(@RequestBody Company company){
        companyRepo.save(company);
        return new ResponseEntity(company, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable UUID id){
        Company company = companyRepo.findById(id).orElse(null);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(company, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Company>> findAll(){
        List<Company> companies = companyRepo.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Company> update(@RequestBody Company company){
        companyRepo.save(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        companyRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
