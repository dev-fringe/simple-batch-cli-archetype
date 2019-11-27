package dev.fringe;

import java.util.Arrays;
import java.util.List;
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
import org.springframework.test.context.ContextConfiguration;

import dev.fringe.model.BatchTable;

@ContextConfiguration({
	 "classpath:launch-context.xml" 
	,"classpath:hibernate-context.xml" 
	,"classpath:common-context.xml" 
	,"classpath:jobs/*.xml"
	,"classpath:jobs/steps/*.xml"})
public class Application extends JobSupport{
	
	private static final String JOB2 = "Job2";
	private static final String JOB3 = "Job3";
	private static final String JOB = "job";
	private static final List<BatchTable> JOBS = Arrays.asList(BatchTable.values());
	
	@Test 
	public void testJobs(){
		try {
			for (BatchTable batchTable : JOBS) {
				System.out.println(batchTable.toString());
			}
			JobExecution execution = jobLauncher.run(context.getBean(JOB, Job.class), new JobParametersBuilder().addString("uid", UUID.randomUUID().toString()).toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
//			JobExecution execution2 = jobLauncher.run(context.getBean(JOB2, Job.class), new JobParametersBuilder().addString("uid", UUID.randomUUID().toString()).toJobParameters());
//			System.out.println("Exit Status : " + execution2.getStatus());
//			JobExecution execution3 = jobLauncher.run(context.getBean(JOB3, Job.class), new JobParametersBuilder().addString("uid", UUID.randomUUID().toString()).toJobParameters());
//			System.out.println("Exit Status : " + execution3.getStatus());
		} catch (BeansException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
