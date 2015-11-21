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

    @Query("SELECT MAX(amount) FROM Donation WHERE P_ID = ?")
    Donation findLGDonation2Proj(int id);

    @Query("SELECT MAX(amount) FROM Donation WHERE U_ID = ?")
    Donation findLargestDonationByUser(int id);

    @Query("SELECT U_ID, SUM(amount) AS total_amount FROM Donation WHERE U_ID GROUP BY U_ID LIMIT 1")
    Donation findLargestDonator();

    @Query("SELECT SUM(amount) FROM Donation WHERE U_ID = ?")
    Donation findUserTotalDonation(int id);

    @Query("SELECT d FROM Donation d")
    Donation findAllDonations();
}
