package com.strategy.application.batch;


import com.strategy.adapter.outbound.persistence.StatisticSoulSelectRepository;
import com.strategy.adapter.outbound.persistence.StatisticSoulselect;
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
public class SoulSelectJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final StatisticSoulSelectRepository statisticSoulSelectRepository;


    public SoulSelectJobConfig(JobBuilderFactory jobBuilderFactory,
                               StepBuilderFactory stepBuilderFactory,
                               EntityManagerFactory entityManagerFactory,
                               StatisticSoulSelectRepository statisticSoulSelectRepository){
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.statisticSoulSelectRepository = statisticSoulSelectRepository;
    }

    @Bean
    public Job statisticSoulJob() throws Exception {
        return jobBuilderFactory.get("statisticSoulJob")
                .start(soulSelectStep())
                .build();
    }

    @Bean
    @JobScope
    public Step soulSelectStep() throws Exception {
        return stepBuilderFactory.get("soulSelectStep")
                .<TacticCharacter, StatisticSoulselect>chunk(10)
                .reader(tacticCharacterSoulIdReader(null))
                .processor(soulSelectProcessor())
                .writer(soulSelectWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<TacticCharacter> tacticCharacterSoulIdReader(
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
    public ItemProcessor<TacticCharacter, StatisticSoulselect> soulSelectProcessor() {
        return tacticCharacter -> {
            //todo refactor
            StatisticSoulselect statisticSoulselect =
                    statisticSoulSelectRepository.getReferenceById(
                            tacticCharacter.getTacticSoulcharacter().getId());
            statisticSoulselect.addCount();
            log.info(">>>>>>>>>>>>> count + 1 >>>>");
            return statisticSoulselect;
        };
    }

    @Bean
    public JpaItemWriter<StatisticSoulselect> soulSelectWriter(){
        log.info(">>>>>>>>>>>>> count write >>>>");
        return new JpaItemWriterBuilder<StatisticSoulselect>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}