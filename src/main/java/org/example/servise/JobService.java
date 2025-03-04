package org.example.servise;

import org.example.models.Job;

import java.util.List;

public interface JobService {
    void createJobTable();

    void addJob(Job job);

    Job getJobById(Long jobId);

    List<Job> sortByExperience(String ascOrDesc);

    Job getJobByEmployeeId(Long employeeId);

    void deleteDescriptionColumn();
}
