package com.theironyard.controllers;
import com.theironyard.Stats;
import com.theironyard.entities.Donation;
import com.theironyard.entities.Project;
import com.theironyard.entities.Project;
import com.theironyard.entities.User;
import com.theironyard.services.DonationRepo;
import com.theironyard.services.ProjectRepo;
import com.theironyard.services.UserRepo;
import com.theironyard.util.PasswordHash;
import jdk.nashorn.internal.ir.PropertyKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Created by Agronis on 11/19/15.
 */
@RestController
public class ironFundController {

    @Autowired
    UserRepo users;

    @Autowired
    ProjectRepo projects;

    @Autowired
    DonationRepo donations;

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (users.count() == 0 ){
            User user = new User();
            user.username = "Admin";
            user.password = PasswordHash.createHash("1234");
            users.save(user);

            Project project = new Project();
            project.goal = 500.00;
            project.user.id = 1;
            project.startDate = LocalDateTime.now();
            project.finishDate = LocalDateTime.now().plusDays(2);
            project.description = "Description for Project";
            project.title = "Alice's Project";
            projects.save(project);

            User user2 = new User();
            user2.username = "Test";
            user2.password = PasswordHash.createHash("4321");
            users.save(user2);

            Project project1 = new Project();
            project1.goal = 500.00;
            project1.user.id = 2;
            project1.startDate = LocalDateTime.now();
            project1.finishDate = LocalDateTime.now().plusDays(2);
            project1.description = "Description for Project";
            project1.title = "Test Project";
            projects.save(project);

        }
    }

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
        User safeUser = new User(user.username);
        return safeUser;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) throws IOException {
        session.invalidate();
    }

    @RequestMapping("/donate")
    public void donate(HttpSession session, HttpServletResponse response, double donate, int id, double amount) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            response.sendRedirect("403");
        }

        Project p = projects.findOne(id);
        p.balance = donate + p.balance;
        projects.save(p);
        Donation d = new Donation();
        d.amount = amount;
        d.date = LocalDateTime.now();
        d.u = users.findOneByUsername(username);
        d.p = projects.findOne(id);
        donations.save(d);
    }

    @RequestMapping (name = "/all", method = RequestMethod.POST)
    public void addProject (
          String title,
          String description,
          String finishDate,
          double goal,
          HttpSession session,
          HttpServletResponse response) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("403");
        }
        Project project = new Project();
        project.title = title;
        project.description = description;
        project.finishDate = LocalDateTime.parse(finishDate);
        project.startDate = LocalDateTime.now();
        project.goal = goal;
        projects.save(project);

    }
    @RequestMapping (name = "/all", method = RequestMethod.PATCH)
    public void editProject (
            int id,
            String title,
            String description,
            HttpSession session,
            double goal,
            HttpServletResponse response)
            throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null || !projects.findOne(id).user.username.equals(username)){
            response.sendRedirect("403");
        }

        Project project = projects.findOne(id);
        project.title = title;
        project.description = description;
        project.goal = goal;
        projects.save(project);

    }

    @RequestMapping(name = "/all", method = RequestMethod.DELETE)
    public void delete(HttpSession session, HttpServletResponse response, int id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null || !projects.findOne(id).user.username.equals(username)) {
            response.sendRedirect("403");
        }
        projects.delete(id);
    }

    @RequestMapping("/single")
    public Project single(HttpSession session, HttpServletResponse response, int id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            response.sendRedirect("403");
        }
        Project p = projects.findOne(id);
        return p;
}

    @RequestMapping(name = "/all", method = RequestMethod.GET)
    public List<Project> all(HttpSession session, HttpServletResponse response) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            response.sendRedirect("403");
        }

        List<Project> all = (List<Project>) projects.findAll();
        return all;
    }

    @RequestMapping("/stats")
    public Stats stats(HttpSession session, HttpServletResponse response) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            response.sendRedirect("403");
        }
        long last = donations.count();
        Donation d = donations.findLast(last);
        Stats s = new Stats();
        s.totalDonations = projects.getTotalDonated();
        s.totalProjects = projects.count();
        s.totalUsers = users.count();
        s.user = d.u.username;
        s.date = d.date;
        s.amount = d.amount;
        String time = s.date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
        s.project = d.p.title;
        s.mostRecent = String.format("%s donated %f on %s towards %s", s.user, s.amount, time, s.project);
        return s;
    }

    @RequestMapping("/allUsers")
    public List<User> allUsers(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            throw new Exception("Not logged in.");
        }
        List<User> allUsers = (List<User>) users.findAll();
        allUsers.stream()
                .filter(pass -> pass.password != null)
                .collect(Collectors.toList());
        return allUsers;
    }

    @RequestMapping("/user")
    public User user(HttpSession session, HttpServletResponse response, int id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username==null) {
            throw new Exception("Not logged in.");
        }
        User user = users.findOneByUsername(username);
        User safeUser = new User(user.projectList, user.donationList, user.username);
        return safeUser;
    }

//    public User findLargestDonator(int id) {
//        User u = new User();
//        List<Donation> donationList = (List<Donation>) donations.findAllDonations();
//        for (Donation donation : donationList) {
//
//        }
//    }
//
}
