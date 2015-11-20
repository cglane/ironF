package com.theironyard.entities;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Agronis on 11/19/15.
 */
@Entity
public class Project {
    @Id
    @GeneratedValue
    public int id;

    @ManyToOne
    public User u;

    @OneToMany(mappedBy = "project")
    public List<Donation> donationList;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public LocalDateTime startDate;

    @Column (nullable = false)
    public LocalDateTime finishDate;

    @Column (nullable = false)
    public double balance = 0;

    @Column (nullable = false)
    public double goal;





}
