package com.strategy.application.batch;

import com.strategy.adapter.outbound.persistence.entity.StatisticPosition;
import com.strategy.adapter.outbound.persistence.jparepository.StatisticPositionRepository;
import com.strategy.enummodel.TacticPositionEnum;
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
public class PositionJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final StatisticPositionRepository statisticPositionRepository;


    public PositionJobConfig(JobBuilderFactory jobBuilderFactory,
                             StepBuilderFactory stepBuilderFactory,
                             EntityManagerFactory entityManagerFactory,
                             StatisticPositionRepository statisticPositionRepository){
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.statisticPositionRepository = statisticPositionRepository;
    }

    @Bean
    public Job statisticPositionJob() throws Exception {
        return jobBuilderFactory.get("statisticPositionJob")
                .start(positionStep())
                .build();
    }

    @Bean
    @JobScope
    public Step positionStep() throws Exception {
        return stepBuilderFactory.get("positionStep")
                .<String, StatisticPosition>chunk(10)
                .reader(positionReader(null))
                .processor(positionProcessor())
                .writer(positionWriter())
                .build();
    }


    @Bean
    @StepScope
    public JpaPagingItemReader<String> positionReader(
            @Value("#{jobParameters[addedCountJobAndDate]}") String addedCountJobAndDate) {

        int addedCount = Integer.parseInt(addedCountJobAndDate.split(",")[0]);

        return new JpaPagingItemReaderBuilder<String>()
                .name("positionReader")
                .pageSize(10)
                .queryString("select t.position from Tactic t order by t.id desc")
                .maxItemCount(addedCount)
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
//    @Bean
//    @StepScope
//    public RepositoryItemReader<Tactic> positionReader(
//            @Value("#{jobParameters[addedCount]}") Long addedCount){
//        return new RepositoryItemReaderBuilder<Tactic>()
//                .name("positionReader")
//                .repository(tacticRepository)
//                .methodName("findAll")
//                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
//                .pageSize(10)
//                .build();
//    }

    @Bean
    @StepScope
    public ItemProcessor<String, StatisticPosition> positionProcessor() {
        return position -> {

            StatisticPosition statisticPosition = null;

            if (position.equals(TacticPositionEnum.POSITION_1.getValue())){
                statisticPosition = statisticPositionRepository.getReferenceById(1L);
            }
            if (position.equals(TacticPositionEnum.POSITION_2.getValue())){
                 statisticPosition = statisticPositionRepository.getReferenceById(2L);
            }
            if (position.equals(TacticPositionEnum.POSITION_3.getValue())){
                 statisticPosition = statisticPositionRepository.getReferenceById(3L);
            }
            if (position.equals(TacticPositionEnum.POSITION_4.getValue())){
                 statisticPosition = statisticPositionRepository.getReferenceById(4L);
            }

            assert statisticPosition != null;
            statisticPosition.addCount();
            log.info(">>>>>>>>>>>>> position count");
            return statisticPosition;
        };
    }

    @Bean
    public JpaItemWriter<StatisticPosition> positionWriter(){
        log.info(">>>>>> write Statistic <<<<<<<<");
        return new JpaItemWriterBuilder<StatisticPosition>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}