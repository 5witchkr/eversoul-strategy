package com.strategy;


import com.strategy.adpater.outbound.persistence.entity.TacticCharacter;
import com.strategy.adpater.outbound.persistence.repository.TacticCharacterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;

@Slf4j
@Configuration
public class SoulJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final StatisticSoulSelectRepository statisticSoulSelectRepository;


    public SoulJobConfig(JobBuilderFactory jobBuilderFactory,
                         StepBuilderFactory stepBuilderFactory,
                         EntityManagerFactory entityManagerFactory,
                         StatisticSoulSelectRepository statisticSoulSelectRepository){
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.statisticSoulSelectRepository = statisticSoulSelectRepository;
    }

    @Bean
    public Job StatisticSoulJob(Long lastId) throws Exception {
        return jobBuilderFactory.get("StatisticSoulJob")
                .start(initSoulSelctStep())
                .next(soulSelectStep())
                .build();
    }

    @Bean
    @JobScope
    public Step initSoulSelctStep() {
        return stepBuilderFactory.get("initSoulSelctStep")
                .<StatisticSoulselect, StatisticSoulselect>chunk(10)
                .reader(initSoulSelectReader())
                .processor(initSoulSelectProcessor())
                .writer(initSoulSelectWriter())
                .build();
    }

    @Bean
    @JobScope
    public Step soulSelectStep() throws Exception {
        return stepBuilderFactory.get("soulSelectStep")
                .<TacticCharacter, StatisticSoulselect>chunk(10)
                .reader(tacticCharacterSoulIdReader())
                .processor(soulSelectProcessor())
                .writer(soulSelectWriter())
                .build();
    }


    @Bean
    @StepScope
    public JpaPagingItemReader<TacticCharacter> tacticCharacterSoulIdReader() {

        return new JpaPagingItemReaderBuilder<TacticCharacter>()
                .name("JpaPagingItemReader")
                .pageSize(10)
                .queryString("select tc from TacticCharacter tc order by tc.id desc")
//                .maxItemCount(22)
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<TacticCharacter, StatisticSoulselect> soulSelectProcessor() {
        return tacticCharacter -> {
            StatisticSoulselect statisticSoulSelect =
                    statisticSoulSelectRepository.getReferenceById(tacticCharacter.getTacticSoulcharacter().getId());
            statisticSoulSelect.addCount();
            log.info(">>>>>>>>>>>>> + 1 >>>>"+ statisticSoulSelect.getId());
            return statisticSoulSelect;
        };
    }

    @Bean
    public JpaItemWriter<StatisticSoulselect> soulSelectWriter(){
        return new JpaItemWriterBuilder<StatisticSoulselect>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


    @Bean
    @StepScope
    public RepositoryItemReader<StatisticSoulselect> initSoulSelectReader() {
        return new RepositoryItemReaderBuilder<StatisticSoulselect>()
                .name("initSoulSelectReader")
                .repository(statisticSoulSelectRepository)
                .methodName("findAll")
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .pageSize(10)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<StatisticSoulselect, StatisticSoulselect> initSoulSelectProcessor() {
        return statisticSoulselect -> {
            statisticSoulselect.setSelectCount(0);
            log.info(">>>>>>>init>>>>>> StatisticSoulselect SelectCount");
            return statisticSoulselect;
        };
    }

    @Bean
    public JpaItemWriter<StatisticSoulselect> initSoulSelectWriter(){
        return new JpaItemWriterBuilder<StatisticSoulselect>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


}