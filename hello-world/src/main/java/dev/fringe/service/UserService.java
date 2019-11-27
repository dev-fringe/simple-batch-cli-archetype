package dev.fringe.service;

import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fringe.model.User;

@Service
public class UserService {

	@Autowired SessionFactory sessionFactory;
	
	public void insert() {
		sessionFactory.getCurrentSession().save(new User(UUID.randomUUID().toString()));
	}
}
