package edu.neu.webdev.bookbasketjavaserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.neu.webdev.bookbasketjavaserver.models.Book;
import edu.neu.webdev.bookbasketjavaserver.models.User;


public interface UserRepository extends CrudRepository<User, Integer>{

	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	User findUserByCredential(@Param("username") String username, @Param("password") String password);
	
	
	@Query("SELECT b FROM User b WHERE b.googleId=:googleId")
	Optional<User> findByGoogleId(@Param("googleId") String googleId);
	

}
