package exceptions;

public class InvalidParameterValue extends Exception{

    @Override
    public String getMessage() {
        return "Invalid parameters of a dot!";
    }
}
