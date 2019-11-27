package dev.fringe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "user2")
public class User2 {
	
	@Id
	private Long id;
	private String name;
	private String test;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date inserted;
	
	public User2(String name) {
		super();
		this.name = name;
	}

	public User2() {
		super();
	}
	

}
