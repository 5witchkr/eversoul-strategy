package com.strategy.application.processor;

import com.strategy.application.batch.SoulSelectJobConfig;
import com.strategy.application.port.inbound.SoulSelectBatchInboundPort;
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
public class SoulSelectBatchProcessor implements SoulSelectBatchInboundPort {

    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final SoulSelectJobConfig soulSelectJobConfig;


    public SoulSelectBatchProcessor(JobLauncher jobLauncher, JobRepository jobRepository, SoulSelectJobConfig soulSelectJobConfig) {
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
        this.soulSelectJobConfig = soulSelectJobConfig;
    }

    @Override
    public void addData(Long addedCount) {

        SimpleJob simpleJob = new SimpleJob();
        simpleJob.setName("statisticSoulJob");
        simpleJob.setJobRepository(jobRepository);

        String addedCountAndDate = addedCount + "," + LocalDate.now();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("addedCountAndDate",addedCountAndDate)
                .toJobParameters();

        List<Step> stepsToExecute = new ArrayList<>();

        try {
            stepsToExecute.add(soulSelectJobConfig.soulSelectStep());
            simpleJob.setSteps(stepsToExecute);
            jobLauncher.run(simpleJob, jobParameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 수행된 적 있는 Batch 입니다.");
        }
    }
}
