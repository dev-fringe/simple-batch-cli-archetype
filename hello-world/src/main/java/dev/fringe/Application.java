package dev.fringe;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
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
@ContextConfiguration("classpath:jobs/*.xml")
public class Application {
	@Autowired JobLauncher jobLauncher;
	@Autowired ApplicationContext context;
	public static List<String> jobs = Arrays.asList("helloWorldJob");
	
	@Test public void testJobs(){
		jobs.forEach(job -> {
			try {
				jobLauncher.run((Job)context.getBean(job), new JobParametersBuilder().addString("uid", String.valueOf(System.currentTimeMillis())).toJobParameters());
			} catch (BeansException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
				e.printStackTrace();
			}
		});
	}
}
