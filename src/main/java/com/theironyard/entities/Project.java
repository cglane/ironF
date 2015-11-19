package com.theironyard.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by Agronis on 11/19/15.
 */
@Entity
public class Project {
    @Id
    @GeneratedValue
    public int id;

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
