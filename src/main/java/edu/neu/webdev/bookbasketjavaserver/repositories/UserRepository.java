package edu.neu.webdev.bookbasketjavaserver.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.neu.webdev.bookbasketjavaserver.models.User;


public interface UserRepository extends CrudRepository<User, Integer>{

}
