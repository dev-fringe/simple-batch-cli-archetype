package dev.fringe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "user_s")
public class UserS {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private String name;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date inserted;
	
	public UserS(String name) {
		super();
		this.name = name;
	}

	public UserS() {
		super();
	}
	

}
