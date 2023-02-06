package com.strategy.application.batch;


import com.strategy.adapter.outbound.persistence.entity.SoulconnectBatchdata;
import com.strategy.adapter.outbound.persistence.jparepository.StatisticSoulConnectRepository;
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
    private final StatisticSoulConnectRepository statisticSoulConnectRepository;

    public SoulConnectJobConfig(JobBuilderFactory jobBuilderFactory,
                                StepBuilderFactory stepBuilderFactory,
                                EntityManagerFactory entityManagerFactory,
                                StatisticSoulConnectRepository statisticSoulConnectRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.statisticSoulConnectRepository = statisticSoulConnectRepository;
    }


    @Bean
    public Job statisticConnectJob() throws Exception {
        return jobBuilderFactory.get("statisticConnectJob")
                .start(soulConnectStep())
                .build();
    }

    @Bean
    @JobScope
    public Step soulConnectStep() throws Exception {
        return stepBuilderFactory.get("soulConnectStep")
                .<TacticCharacter, SoulconnectBatchdata>chunk(10)
                .reader(soulConnectTacticCharacterReader(null))
                .processor(soulConnectTacticCharacterProcessor())
                .writer(soulConnectTacticCharacterWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<TacticCharacter> soulConnectTacticCharacterReader(
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
    public ItemProcessor<TacticCharacter, SoulconnectBatchdata> soulConnectTacticCharacterProcessor() {
        return tacticCharacter -> SoulconnectBatchdata.builder()
                .tacticId(tacticCharacter.getTactic().getId())
                .soulId(tacticCharacter.getTacticSoulcharacter().getId())
                .build();
    }

    @Bean
    public JpaItemWriter<SoulconnectBatchdata> soulConnectTacticCharacterWriter(){
        log.info(">>>>>>>>>>>>> count write >>>>");
        return new JpaItemWriterBuilder<SoulconnectBatchdata>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
