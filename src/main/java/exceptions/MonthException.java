package exceptions;


public class MonthException extends Exception{
    public MonthException (String month) {super(String.format("Month %s not supported", month));}

}
