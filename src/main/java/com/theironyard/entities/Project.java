package com.theironyard.entities;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Agronis on 11/19/15.
 */
@Entity
public class Project {
    @Id
    @GeneratedValue
    public Integer id;

//    @ManyToOne
////    public User user;
//
//    @OneToMany(mappedBy = "p")
//    public List<Donation> donationList;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public String startDate;

    @Column (nullable = false)
    public String finishDate;

    @Column (nullable = false)
    public Double balance = 0.0;

    @Column (nullable = false)
    public Double goal;

    @Column (nullable = false)
    public int percentage;

    public String imageName;
    public String originalName;

}
