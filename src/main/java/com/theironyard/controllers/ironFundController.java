package com.theironyard.controllers;
import com.theironyard.Stats;
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
import java.util.List;

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

    @RequestMapping("/donate")
    public void donate(HttpSession session, double donate, int id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            throw new Exception("Not logged in.");
        }
//        User user = users.findOneByUsername(username);
//        user.donated = user.donated + donate;
        Project p = projects.findOne(id);
        p.balance = donate + p.balance;
        projects.save(p);
    }

    @RequestMapping("/delete")
    public void delete(HttpSession session, int id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            throw new Exception("Not logged in.");
        }
        projects.delete(id);
    }

    @RequestMapping("/single")
    public Project single(HttpSession session, int id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            throw new Exception("Not logged in.");
        }

        Project p = projects.findOne(id);
        return p;
    }

    @RequestMapping("/all")
    public List<Project> all(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            throw new Exception("Not logged in.");
        }

        List<Project> all = (List<Project>) projects.findAll();
        return all;
    }

//    @RequestMapping("/stats")
//    public Stats stats(HttpSession session) throws Exception {
//        String username = (String) session.getAttribute("username");
//        if (username==null) {
//            throw new Exception("Not logged in.");
//        }
//        long i = projects.count();
//        Project p = projects.findOne((int) i);
//        Stats s = new Stats();
//        s.totalDonations = projects.getTotalDonated();
//        s.totalProjects = projects.count();
//        s.totalUsers = users.count();
//        s.date = p.startDate;
//        s.user =
//
//
//        return s;
//    }

    
}
