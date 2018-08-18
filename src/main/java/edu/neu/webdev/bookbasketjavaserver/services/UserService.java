package edu.neu.webdev.bookbasketjavaserver.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.webdev.bookbasketjavaserver.models.User;
import edu.neu.webdev.bookbasketjavaserver.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	HttpSession currentSession;
	

	@GetMapping("/api/user")
	public Iterable<User> findAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		
		session.setAttribute("currentUser", user);
		currentSession = session;
		return userRepository.save(user);
	}

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		Optional<User> data = userRepository.findById(id);
		if (data.isPresent())
			return data.get();
		return null;

	}
	
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpSession session, HttpServletResponse response) {
		User cUser = (User) userRepository.findUserByCredential(user.getUsername(), user.getPassword());
		if (cUser != null) {
			
			session.setAttribute("currentUser", cUser);
			currentSession = session;
			return cUser;
		}
		response.setStatus(422);
		return null;
	}

	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session){

	User newUser = (User) currentSession.getAttribute("currentUser");
	int idUser = newUser.getUserId() ;
	user.setUserId(idUser);
	userRepository.save(user);
	return user;
	}

	@GetMapping(value = "/api/logout")
	public User logout (HttpSession session)
	{
	currentSession=session;
	return null;
	}
	
	@GetMapping("/api/profile")
	public User getProfile(HttpSession session) {
	User newUser;
	System.out.println("inside get profile!!");
	newUser =(User) currentSession.getAttribute("currentUser");
	
	return newUser;
	}
	
}
