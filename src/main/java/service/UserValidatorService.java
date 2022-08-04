package service;

import com.google.common.base.Objects;
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
        if (Objects.equal(user.getUsername(), username) && Objects.equal(user.getPassword(), UserDAO.md5Converter(password))){
            session.setAttribute("CurrentUser", user);
            return true;
        }
        return false;
    }

    public static boolean checkRegistrationRequiredInputs(String... inputs) {
        for (String input : inputs) {
            if (input.isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
