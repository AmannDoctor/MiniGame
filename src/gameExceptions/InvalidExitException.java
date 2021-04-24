package gameExceptions;

import java.io.IOException;

public class InvalidExitException extends IOException {
    public InvalidExitException(){
        super("There seems to be an issue with the Exits");

    }
    public InvalidExitException(String msg){
        super(msg);
    }
}