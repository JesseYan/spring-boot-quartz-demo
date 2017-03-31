package com.kaviddiss.bootquartz.service;

import com.kaviddiss.bootquartz.job.SampleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by david on 2015-01-20.
 */
@Service
public class SampleService {

    @Autowired
    private Scheduler scheduler;

    private static final Logger LOG = LoggerFactory.getLogger(SampleService.class);

    public void hello() {
        LOG.info("Hello World!");
    }

    @PostConstruct
    public void test() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
                .storeDurably(true)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

}
