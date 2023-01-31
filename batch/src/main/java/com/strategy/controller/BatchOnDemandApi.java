package com.strategy.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/batch-admin")
public class BatchOnDemandApi {

    @Value("${devValue}")
    private String value;

    private final JobLauncher jobLauncher;

    private final Job job;

    public BatchOnDemandApi(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @PostMapping("/soulselect")
    public String postSoul(@RequestParam String devValue,
                         @RequestParam Long lastId)
            throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {

        if (!devValue.equals(value)) return "X";

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("requestLastId",lastId)
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
        return "O";
    }
}
