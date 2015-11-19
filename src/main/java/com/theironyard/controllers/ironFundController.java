package com.theironyard.controllers;
import com.theironyard.entities.User;
import com.theironyard.services.ProjectRepo;
import com.theironyard.services.UserRepo;
import com.theironyard.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            HttpServletResponse response,
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
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
    }




    
}
