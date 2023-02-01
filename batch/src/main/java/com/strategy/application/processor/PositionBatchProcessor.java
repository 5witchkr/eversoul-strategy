package com.strategy.application.processor;


import com.strategy.application.batch.PositionJobConfig;
import com.strategy.application.port.inbound.PositionBatchInboundPort;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PositionBatchProcessor implements PositionBatchInboundPort {

    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PositionJobConfig positionJobConfig;

    public PositionBatchProcessor(JobLauncher jobLauncher, JobRepository jobRepository, PositionJobConfig positionJobConfig) {
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
        this.positionJobConfig = positionJobConfig;
    }

    @Override
    public void addData(Long addedCount) {
        SimpleJob simpleJob = new SimpleJob();
        simpleJob.setName("statisticPositionJob");
        simpleJob.setJobRepository(jobRepository);

        if (addedCount == 0) return;

        String addedCountJobAndDate = addedCount + "," + "Position" + LocalDate.now();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("addedCountJobAndDate",addedCountJobAndDate)
                .toJobParameters();

        List<Step> stepsToExecute = new ArrayList<>();

        try {
            stepsToExecute.add(positionJobConfig.positionStep());
            simpleJob.setSteps(stepsToExecute);
            jobLauncher.run(simpleJob, jobParameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 수행된 적 있는 Batch 입니다.");
        }
    }
}
