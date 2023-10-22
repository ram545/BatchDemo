package com.example.controller;

import com.example.model.JobTrigger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/batch/job")
public class JobTriggerController {
    @Autowired
    @Qualifier(value="Csv File Job")
    private Job csvJob;

    @Autowired
    @Qualifier(value="Fixed Position Job")
    private Job fixedJob;

    @Autowired
    private JobLauncher jobLauncher;

    @PostMapping(value="/start")
    private void jobStart(@RequestBody JobTrigger jobTrigger){
        JobParameters jobParams = new JobParametersBuilder()
                .addString("Job Type", jobTrigger.getJobName())
                .addLong("Start Time", System.currentTimeMillis())
                .toJobParameters();

        try {
            if("fixed".equals(jobTrigger.getJobName()))
                jobLauncher.run(fixedJob, jobParams);
            else
                jobLauncher.run(csvJob, jobParams);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }

    }
}
