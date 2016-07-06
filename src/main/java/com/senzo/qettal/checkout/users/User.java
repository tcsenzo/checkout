package com.senzo.qettal.checkout.users;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	@Column(name="created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	/**
	 * @deprecated Hibernate eyes only
	 */
	User() {
	}
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Optional<User> save(Users users) {
		return Optional.of(users.save(this));
	}
	
}
