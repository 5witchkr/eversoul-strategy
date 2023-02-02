package com.strategy.application.processor;


import com.strategy.application.batch.SoulConnectJobConfig;
import com.strategy.application.port.inbound.portbatch.SoulConnectBatchInboundPort;
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
public class SoulConnectBatchProcessor implements SoulConnectBatchInboundPort {

    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final SoulConnectJobConfig soulConnectJobConfig;

    public SoulConnectBatchProcessor(JobLauncher jobLauncher, JobRepository jobRepository, SoulConnectJobConfig soulConnectJobConfig) {
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
        this.soulConnectJobConfig = soulConnectJobConfig;
    }


    @Override
    public void addData(Long addedCount) {

        SimpleJob simpleJob = new SimpleJob();
        simpleJob.setName("statisticConnectJob");
        simpleJob.setJobRepository(jobRepository);

        if (addedCount == 0) return;

        String addedCountJobAndDate = addedCount + "," + "SoulConnectBatchData" + LocalDate.now();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("addedCountJobAndDate",addedCountJobAndDate)
                .toJobParameters();

        List<Step> stepsToExecute = new ArrayList<>();

        try {
            stepsToExecute.add(soulConnectJobConfig.soulConnectStep1());
            stepsToExecute.add(soulConnectJobConfig.soulConnectStep2());
            simpleJob.setSteps(stepsToExecute);
            jobLauncher.run(simpleJob, jobParameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 수행된 적 있는 Batch 입니다.");
        }
    }
}
