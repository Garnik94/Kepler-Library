package service;

import exceptions.AbsentUserException;
import model.User;
import service.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserValidatorService {

    public static boolean validateUser(String username, String password, HttpServletRequest request)
            throws AbsentUserException {
        User user = UserDAO.getUser(new User(username, password));
        HttpSession session = request.getSession();
        session.setAttribute("CurrentUser", user);
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }


}
