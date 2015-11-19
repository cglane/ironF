package com.theironyard.services;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Agronis on 11/19/15.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    
}
