package sample.Exceptions;

public class MyException extends Exception{
    public MyException(){
        super("MyException");
    }
    public MyException(String exception){
        super(exception);
    }
}
