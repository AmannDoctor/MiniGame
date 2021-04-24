package gameExceptions;

import java.io.IOException;

public class InvalidItemException extends IOException {
    public InvalidItemException(){
        super("There seems to be an issue with the Items");

    }
    public InvalidItemException(String message){
        super(message);
    }

}