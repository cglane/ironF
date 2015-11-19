package com.theironyard.controllers;
import com.theironyard.entities.Project;
import com.theironyard.entities.User;
import com.theironyard.services.ProjectRepo;
import com.theironyard.services.UserRepo;
import com.theironyard.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by Agronis on 11/19/15.
 */
@RestController
public class ironFundController {

    @Autowired
    UserRepo users;

    @Autowired
    ProjectRepo projects;



    @RequestMapping("/login")
    public User login(
            HttpSession session,
            String username,
            String password
    ) throws Exception {
        User user = users.findOneByUsername(username);
        if (user==null) {
            user = new User();
            user.username = username;
            user.password = PasswordHash.createHash(password);
            users.save(user);
        } else if (!PasswordHash.validatePassword(password, user.password)) {
            throw new Exception("Wrong Password.");
        }
        session.setAttribute("username", username);
        return user;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) throws IOException {
        session.invalidate();
    }

    @RequestMapping ("/create")
    public void addProject (
          String title,
          String description,
          String finishDate,
          double balance, double goal,
          HttpSession session) throws Exception {

        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not Logged in");

        }
        User user = users.findOneByUsername(username);

        Project project = new Project();
        project.title = title;
        project.balance = balance;
        project.description = description;
        project.finishDate = LocalDateTime.parse(finishDate);
        project.startDate = LocalDateTime.now();
        project.goal = goal;
        projects.save(project);

    }
    @RequestMapping ("/edit")
    public void editProject (
            int id,
            String title,
            String description,
            HttpSession session,
            double goal)
            throws Exception {
        if (session.getAttribute("username") == null){
            throw new Exception("Not Logged in");
        }

        Project project = projects.findOne(id);
        project.title = title;
        project.description = description;
        project.goal = goal;
        projects.save(project);

    }
    
}
