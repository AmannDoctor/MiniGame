package gameExceptions;
import java.io.IOException;
public class InvalidSaveException extends IOException {
    public InvalidSaveException(){

    }
    public InvalidSaveException(String msg){
        super(msg);
    }
}