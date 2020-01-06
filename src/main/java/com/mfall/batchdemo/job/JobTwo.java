package com.mfall.batchdemo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class JobTwo implements Tasklet {
    private static Logger logger = LoggerFactory.getLogger(JobTwo.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        logger.info(".... JOB TWO START ....");
        logger.info("############## ... #############");
        logger.info("############## ... #############");
        logger.info("######### JOB TWO DONE ##########");

        return RepeatStatus.FINISHED;
    }
}
