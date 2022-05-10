package exceptions;

public class AbsentUserException extends Exception {

    public AbsentUserException() {
        super("Username absences in database");
    }

}
