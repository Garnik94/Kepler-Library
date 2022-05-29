package service;

import exceptions.AbsentUserException;
import model.User;
import service.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserValidatorService {

    public boolean validateUser(String username, String password, HttpServletRequest request)
            throws AbsentUserException {
        User user = UserDAO.getUser(new User(username, password));
        HttpSession session = request.getSession();
        session.setAttribute("CurrentUser", user);
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

}
