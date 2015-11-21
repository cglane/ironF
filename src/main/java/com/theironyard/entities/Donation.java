package com.theironyard.entities;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Agronis on 11/19/15.
 */
@Entity
public class Donation {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    public int id;

    @Column(nullable = false)
    public double amount;

    @Column(nullable = false)
    public LocalDateTime date;

    @ManyToOne
    public Project p;

    @ManyToOne
    public User u;



}
