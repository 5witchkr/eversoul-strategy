package com.strategy.application.processor.soulconnect;


import com.strategy.adapter.outbound.persistence.entity.SoulconnectBatchdata;
import com.strategy.adapter.outbound.persistence.entity.StatisticSoulconnect;
import com.strategy.application.batch.SoulConnectJobConfig;
import com.strategy.application.port.inbound.portbatch.SoulConnectBatchInboundPort;
import com.strategy.application.port.outbound.SoulconnectBatchdataOutboundPort;
import com.strategy.application.port.outbound.StatisticSoulconnectOutboundPort;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoulConnectBatchProcessor implements SoulConnectBatchInboundPort {

    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final SoulConnectJobConfig soulConnectJobConfig;
    private final SoulconnectBatchdataOutboundPort soulconnectBatchdataOutboundPort;
    private final StatisticSoulconnectOutboundPort statisticSoulconnectOutboundPort;

    public SoulConnectBatchProcessor(JobLauncher jobLauncher, JobRepository jobRepository, SoulConnectJobConfig soulConnectJobConfig, SoulconnectBatchdataOutboundPort soulconnectBatchdataOutboundPort, StatisticSoulconnectOutboundPort statisticSoulconnectOutboundPort) {
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
        this.soulConnectJobConfig = soulConnectJobConfig;
        this.soulconnectBatchdataOutboundPort = soulconnectBatchdataOutboundPort;
        this.statisticSoulconnectOutboundPort = statisticSoulconnectOutboundPort;
    }

    @Override
    public void addData(List<Long> addedTacticIds) {
        addedTacticIds.stream()
                .map(this::getSortedSouls)
                .forEach(this::countConnectSouls);
    }

    private List<Long> getSortedSouls(Long addedTacticId) {
        return soulconnectBatchdataOutboundPort.findAllByTacticId(addedTacticId)
                .stream()
                .sorted(Comparator.comparingLong(SoulconnectBatchdata::getSoulId).reversed())
                .map(SoulconnectBatchdata::getSoulId)
                .collect(Collectors.toList());
    }

    private void countConnectSouls(List<Long> sortedSouls) {
        sortedSouls.forEach(aLong -> {
            for (int index = sortedSouls.indexOf(aLong) + 1; index < sortedSouls.size(); index++){
                StatisticSoulconnect statisticSoulconnect =
                        statisticSoulconnectOutboundPort.findByConnectSoulAndConnectedSoul(aLong, sortedSouls.get(index))
                                .orElse(StatisticSoulconnect.builder().connectSoul(aLong).connectedSoul(sortedSouls.get(index)).build());
                statisticSoulconnect.addCount();
                statisticSoulconnectOutboundPort.save(statisticSoulconnect);
            }
        });
    }


    private void startBatch(Long addedCount) {
        SimpleJob simpleJob = new SimpleJob();
        simpleJob.setName("statisticConnectJob");
        simpleJob.setJobRepository(jobRepository);

        if (addedCount == 0) return;

        String addedCountJobAndDate = addedCount + "," + "SoulConnectBatch" + LocalDate.now();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("addedCountJobAndDate",addedCountJobAndDate)
                .toJobParameters();

        List<Step> stepsToExecute = new ArrayList<>();

        try {
            stepsToExecute.add(soulConnectJobConfig.soulConnectStep());
            simpleJob.setSteps(stepsToExecute);
            jobLauncher.run(simpleJob, jobParameters);
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 수행된 적 있는 Batch 입니다.");
        }
    }
}
