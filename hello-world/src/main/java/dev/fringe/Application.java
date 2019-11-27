package dev.fringe;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.ContextConfiguration;

import dev.fringe.model.Table;
import lombok.extern.log4j.Log4j2;

@ContextConfiguration({
	 "classpath:launch-context.xml" 
	,"classpath:hibernate-context.xml" 
	,"classpath:common-context.xml" 
	,"classpath:jobs/*.xml"
	,"classpath:jobs/steps/*.xml"})
@Log4j2
public class Application extends JobSupport{
	
	private static final String JOB = "job";
	private static final List<Table> TABLES = Arrays.asList(Table.values());
	
	@Test public void testJobs(){
		try {
			for (Table batch : TABLES) {
				log.info(batch.toString());
				Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(String.format("/%s.properties", batch.toString())));
				JobExecution execution = jobLauncher.run(context.getBean(JOB, Job.class), 
						new JobParametersBuilder()
						.addString("uid", UUID.randomUUID().toString())
						.addString("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
						.addString("table", batch.toString())
						.addString("reader.names", props.getProperty("reader.names"))
						.addString("writer.names", props.getProperty("writer.names")).toJobParameters());
				System.out.println("Exit Status : " + execution.getStatus());
			}
		} catch (BeansException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException | IOException e) {
			e.printStackTrace();
		}
	}
}
