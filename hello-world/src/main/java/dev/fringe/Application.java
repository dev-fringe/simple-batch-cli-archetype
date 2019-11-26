package dev.fringe;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	 "classpath:launch-context.xml" 
	,"classpath:hibernate-context.xml" 
	,"classpath:common-context.xml" 
	,"classpath:jobs/*.xml"
	,"classpath:xml/*.xml"})
public class Application {
	
	@Autowired JobLauncher jobLauncher;
    @Autowired ApplicationContext context;
	
	@Test public void testJobs(){
		try {
			JobExecution execution = jobLauncher.run(context.getBean("Job2", Job.class), new JobParametersBuilder().addString("uid", UUID.randomUUID().toString()).toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (BeansException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
