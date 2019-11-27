package dev.fringe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import dev.fringe.service.UserService;

@ContextConfiguration({
	 "classpath:launch-context.xml" 
	,"classpath:hibernate-context.xml" 
	,"classpath:common-context.xml" 
	,"classpath:jobs/*.xml"
	,"classpath:jobs/steps/*.xml"})
public class ApplicationInsert extends JobSupport{
	
	@Autowired UserService service;
	@Test 
	public void testJobs(){
		try {
			JobExecution execution = jobLauncher.run(context.getBean("jobBulk", Job.class),
					new JobParametersBuilder().addString("uid", UUID.randomUUID().toString())
							.addString("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
							.toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (BeansException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
