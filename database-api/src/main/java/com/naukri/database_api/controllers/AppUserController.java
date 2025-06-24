package com.naukri.database_api.controllers;

import com.naukri.database_api.models.AppUser;
import com.naukri.database_api.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/user")
public class AppUserController {

    /**
     * Bean → Spring-managed object (created using @Component, @Service, etc.)
     * IoC Container → Controls object creation & injection (Inversion of Control)
     * Manual Object → Created using 'new' keyword (not recommended in Spring)
     */

    /**
     * Constructor-based Dependency Injection of AppUserRepository.
     * Spring Boot's IoC container automatically provides the repository bean.
     * This allows the controller to access database operations without manual object creation.
     */

    AppUserRepository appUserRepository;  //springboot will provide object of app user repository from IOC container

    @Autowired
    public AppUserController(AppUserRepository userRepo){
        this.appUserRepository = userRepo;
    }

    @PostMapping("/save")
    public ResponseEntity createUser(@RequestBody AppUser user){
        // What is status code ?
        // So to create the user object we will have all the property set but only id property will not be set
        // Hibernate will see as this user object is not having id property set
        // So, I Should create a new record inside user table.
        appUserRepository.save(user);   //This will save the user into the database using Spring Data JPA.
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @GetMapping("/email/{emailId}")
    public ResponseEntity getUserByEmail(@PathVariable String emailId){
        AppUser user = appUserRepository.findByEmail(emailId);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable UUID id){
        AppUser user = appUserRepository.findById(id).orElse(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody AppUser user){
        // This user object will have some of the fields updated.
        // And to update user it should definetly have id in it
        // Update -> We use update functinality only when the record is already present in the database.
        // You need to do what ? You need to update some of its field
        // So, hibernate is saying if in your user object already id property is set already
        // So, I will do what i am going to search the record which is having the same Id which you are passing in user object
        // If i will be able to find that record i will update all the updated fields which you are pasisng in the user object
        // If i will not be able to find the record then i will create a new record in the table.
        appUserRepository.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable UUID id){
        appUserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
