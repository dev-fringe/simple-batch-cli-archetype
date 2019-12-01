package dev.fringe;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import dev.fringe.service.UserService;

@ContextConfiguration({
	 "classpath:launch-context.xml" 
	,"classpath:hibernate-context.xml" 
	,"classpath:common-context.xml" 
//	,"classpath:transaction-context.xml" 	
	,"classpath:jpa-context.xml"
	,"classpath:jobs/*.xml"
	,"classpath:jobs/steps/*.xml"})
public class ApplicationUnitTest extends JobSupport{
	
	@Autowired UserService service;
	@Test 
	public void testJobs() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		service.selectS();
	}
}
