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
        jobDao.getJobById(jobId);
        return null;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        jobDao.sortByExperience(ascOrDesc);
        return null;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        jobDao.getJobByEmployeeId(employeeId);
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {
jobDao.deleteDescriptionColumn();
    }
}
