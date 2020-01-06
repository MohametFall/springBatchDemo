package com.mfall.batchdemo.config;

import com.mfall.batchdemo.domain.Etudiant;
import com.mfall.batchdemo.job.EtudiantProcessor;
import com.mfall.batchdemo.job.EtudiantWriter;
import com.mfall.batchdemo.job.JobOne;
import com.mfall.batchdemo.job.JobTwo;
import com.mfall.batchdemo.repository.EtudiantRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    EntityManagerFactory managerFactory;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private EtudiantRepository etudiantRepo;

    private static Logger logger = LoggerFactory.getLogger(JobOne.class);

    @Scheduled(cron = "0 */1 * * * ?")
    public void perform() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(demoJob(), params);
    }



    @Bean
    public RepositoryItemReader<Etudiant> reader() {
        logger.info("######## BATCH READER ETUDIANT ########");
        RepositoryItemReader<Etudiant> etudiantReader = new RepositoryItemReader<Etudiant>();
        etudiantReader.setRepository(etudiantRepo);
        etudiantReader.setMethodName("findAll");
        etudiantReader.setPageSize(40);
        HashMap<String, Direction> sorts = new HashMap<>();
        sorts.put("id", Direction.DESC);
        etudiantReader.setSort(sorts);
        return etudiantReader;
    }

    @Bean
    public RepositoryItemWriter<Etudiant> writer() {
        logger.info("######## BATCH WRITER ETUDIANT ########");
        RepositoryItemWriter<Etudiant> itemWriter = new RepositoryItemWriter<Etudiant>();
        itemWriter.setRepository(etudiantRepo);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    @Bean
    public ItemReader<Etudiant> productItemReader() throws Exception {
        logger.info("######## BATCH JPA READER ETUDIANT ########");
        JpaPagingItemReader<Etudiant> reader = new JpaPagingItemReader<Etudiant>();
        reader.setEntityManagerFactory(managerFactory);
        reader.setQueryString("select e from Etudiant e");
        reader.setPageSize(3);
        reader.afterPropertiesSet();
        return reader;
    }

    @Bean
    public Step step1() throws Exception {
        logger.info("######## JPA BATCH LAUNCHING ETUDIANT JOB ########");
        return this.steps.get("step1")
                .<Etudiant, Etudiant> chunk(3).reader(productItemReader())
                .writer(new EtudiantWriter()).build();
    }

    @Bean
    public Job demoJob() throws Exception {
        return jobs.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

}
