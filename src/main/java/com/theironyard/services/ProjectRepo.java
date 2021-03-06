package com.theironyard.services;
import com.theironyard.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Agronis on 11/19/15.
 */
public interface ProjectRepo extends CrudRepository<Project, Integer> {

    @Query("SELECT SUM(balance) FROM Project")
    double getTotalDonated();

}
