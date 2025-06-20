package com.naukri.database_api.repositories;

import com.naukri.database_api.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkillRepo extends JpaRepository<Skill, UUID> {

    public Skill findByName(String name); //this function will be implemented by jpa and hibernate for us

}
