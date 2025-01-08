package org.example.servise.impl;

import org.example.dao.Impl.JobDaoImpl;
import org.example.dao.JobDao;
import org.example.models.Job;
import org.example.servise.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    private JobDao jobDao=new JobDaoImpl();
    @Override
    public void createJobTable() {
        jobDao.createJobTable();
    }

    @Override
    public void addJob(Job job) {
jobDao.addJob(job);
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobDao.getJobById(jobId);

    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return  jobDao.sortByExperience(ascOrDesc);

    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);

    }

    @Override
    public void deleteDescriptionColumn() {
jobDao.deleteDescriptionColumn();
    }
}
