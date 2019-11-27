package dev.fringe.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import dev.fringe.service.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SourceInsert implements Tasklet {

	private static final String HELLO_WORLD = "Hello, world!";

	@Autowired UserService userService;
	
	public RepeatStatus execute(StepContribution contribution, ChunkContext context) throws Exception {
		String name = (String) context.getStepContext().getJobParameters().get("uid");
		ExecutionContext jobContext = context.getStepContext().getStepExecution().getExecutionContext();
		jobContext.put("uid", name);
		log.debug(name);
		log.debug(HELLO_WORLD);
		userService.insert();
		return RepeatStatus.FINISHED;
	}

}
