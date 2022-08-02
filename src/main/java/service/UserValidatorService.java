package service;

import exceptions.AbsentUserException;
import model.User;
import service.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class UserValidatorService {

    public static boolean validateUser(Connection connection, String username, String password, HttpServletRequest request)
            throws AbsentUserException {
        User user = UserDAO.getUser(connection, new User(username, password));
        HttpSession session = request.getSession();
        if (user.getUsername().equals(username) && user.getPassword().equals(UserDAO.md5Converter(password))){
            session.setAttribute("CurrentUser", user);
            return true;
        }
        return false;
    }

}
