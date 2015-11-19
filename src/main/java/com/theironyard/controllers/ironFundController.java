package com.theironyard.controllers;
import com.theironyard.entities.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by Agronis on 11/19/15.
 */
@RestController
public class ironFundController {

    @RequestMapping ("/add-project")
    public String addProject (String title,
                              String description,
                              LocalDateTime startDate,
                              LocalDateTime finishDate,
                              double balance, double goal,
                              HttpSession session) throws Exception {
        String username = (String)session.getAttribute("username");
        if (username == null){
           throw new Exception("Not Logged in");
        }

    
}
