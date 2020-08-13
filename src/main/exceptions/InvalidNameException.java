package exceptions;

//already exists in java NamingException

public class InvalidNameException extends Exception {
    public InvalidNameException(String msg) {
        super(msg);
    }
}
