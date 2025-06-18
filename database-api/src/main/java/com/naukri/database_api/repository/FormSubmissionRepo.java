package com.naukri.database_api.repository;

import com.naukri.database_api.models.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormSubmissionRepo extends JpaRepository<FormSubmission, UUID> {
}
