package com.hoaxify_1.hoaxify_1.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String displayName;
	 
	@NotNull
	
	private String password;

}
