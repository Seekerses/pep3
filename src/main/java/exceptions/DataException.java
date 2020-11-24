package exceptions;

public class DataException extends Exception{

    @Override
    public String getMessage() {
        return "Invalid type of parameters of a dot!";
    }
}
