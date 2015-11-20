package com.theironyard.services;
import com.theironyard.entities.Donation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Agronis on 11/19/15.
 */
public interface DonationRepo extends CrudRepository<Donation, Integer> {

    @Query("SELECT d FROM Donation d WHERE ROWNUM = ?")
    Donation findLast(long last);
    
}
