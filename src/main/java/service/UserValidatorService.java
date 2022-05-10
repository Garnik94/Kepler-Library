package service;

import exceptions.AbsentUserException;
import model.User;
import service.dao.UserDAO;

public class UserValidatorService {

    public boolean validateUser(String username, String password) throws AbsentUserException {
        User user = UserDAO.getUser(username);
        if (user != null){
            return user.getUsername().equals(username) && user.getPassword().equals(password);
        }
        throw new AbsentUserException();
    }

}
