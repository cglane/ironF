package com.theironyard.entities;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.List;

/**
 * Created by Agronis on 11/19/15.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    public int id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;


    @Column(nullable = false)
    public boolean admin = false;

    public User(){}
    public User(int id, String username) {

        this.id = id;
        this.username = username;
    }

    //    @Column(nullable = false)
//    public MultipartFile photo;


    
}
