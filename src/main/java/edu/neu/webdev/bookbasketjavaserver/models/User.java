package edu.neu.webdev.bookbasketjavaserver.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String type;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String googleId;
	
	@OneToMany(mappedBy="user")
	private List<Book> books;
	
	@OneToMany(mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Review> review;
	
	@OneToMany(mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Note> notes;
	
	public int getUserId() {
		return id;
	}
	public void setUserId(int userId) {
		this.id = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGoogleId() {
		return googleId;
	}
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	
}
