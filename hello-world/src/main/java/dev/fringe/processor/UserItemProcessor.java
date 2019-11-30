package dev.fringe.processor;

import org.springframework.batch.item.ItemProcessor;

import dev.fringe.model.User;

public class UserItemProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User item) throws Exception {
		return item;
	}

}
