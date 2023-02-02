package com.strategy.application.batch;


import com.strategy.adapter.outbound.persistence.SoulconnectBatchdata;
import com.strategy.adapter.outbound.persistence.SoulconnectBatchdataRepository;
import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
public class SoulConnectJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final SoulconnectBatchdataRepository soulconnectBatchdataRepository;

    public SoulConnectJobConfig(JobBuilderFactory jobBuilderFactory,
                                StepBuilderFactory stepBuilderFactory,
                                EntityManagerFactory entityManagerFactory,
                                SoulconnectBatchdataRepository soulconnectBatchdataRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.soulconnectBatchdataRepository = soulconnectBatchdataRepository;
    }


    @Bean
    public Job statisticConnectJob() throws Exception {
        return jobBuilderFactory.get("statisticConnectJob")
                .start(soulConnectStep1())
                .next(soulConnectStep2())
                .build();
    }



    @Bean
    @JobScope
    public Step soulConnectStep1() throws Exception {
        return stepBuilderFactory.get("soulConnectStep1")
                .<TacticCharacter, SoulconnectBatchdata>chunk(10)
                .reader(soulConnectTacticCharacterReader1(null))
                .processor(soulConnectTacticCharacterProcessor1())
                .writer(soulConnectTacticCharacterWriter1())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<TacticCharacter> soulConnectTacticCharacterReader1(
            @Value("#{jobParameters[addedCountJobAndDate]}") String addedCountJobAndDate) {

        int addedCount = Integer.parseInt(addedCountJobAndDate.split(",")[0]);

        return new JpaPagingItemReaderBuilder<TacticCharacter>()
                .name("JpaPagingItemReader")
                .pageSize(10)
                .queryString("select tc from TacticCharacter tc order by tc.id desc")
                .maxItemCount(addedCount)
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<TacticCharacter, SoulconnectBatchdata> soulConnectTacticCharacterProcessor1() {
        return tacticCharacter -> SoulconnectBatchdata.builder()
                        .id(tacticCharacter.getTactic().getId())
                        .build();
    }

    @Bean
    public JpaItemWriter<SoulconnectBatchdata> soulConnectTacticCharacterWriter1(){
        log.info(">>>>>>>>>>>>> count write >>>>");
        return new JpaItemWriterBuilder<SoulconnectBatchdata>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


    @Bean
    @JobScope
    public Step soulConnectStep2() throws Exception {
        return stepBuilderFactory.get("soulConnectStep2")
                .<TacticCharacter, SoulconnectBatchdata>chunk(10)
                .reader(soulConnectTacticCharacterReader2(null))
                .processor(soulConnectTacticCharacterProcessor2())
                .writer(soulConnectTacticCharacterWriter2())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<TacticCharacter> soulConnectTacticCharacterReader2(
            @Value("#{jobParameters[addedCountJobAndDate]}") String addedCountJobAndDate) {

        int addedCount = Integer.parseInt(addedCountJobAndDate.split(",")[0]);

        return new JpaPagingItemReaderBuilder<TacticCharacter>()
                .name("JpaPagingItemReader")
                .pageSize(10)
                .queryString("select tc from TacticCharacter tc order by tc.id desc")
                .maxItemCount(addedCount)
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<TacticCharacter, SoulconnectBatchdata> soulConnectTacticCharacterProcessor2() {
        return tacticCharacter -> {
            SoulconnectBatchdata soulconnectBatchdata =
                    soulconnectBatchdataRepository.findById(tacticCharacter.getTactic().getId())
                            .orElseThrow();
            soulconnectBatchdata.addSoulId(tacticCharacter.getTacticSoulcharacter().getId());
            return soulconnectBatchdata;
        };
    }

    @Bean
    public JpaItemWriter<SoulconnectBatchdata> soulConnectTacticCharacterWriter2(){
        log.info(">>>>>>>>>>>>> count write >>>>");
        return new JpaItemWriterBuilder<SoulconnectBatchdata>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
