package gameExceptions;

import java.io.IOException;

public class InvalidCommandException extends IOException {
    public InvalidCommandException(){
        super("There seems to be an issue with the commands");

    }
    public InvalidCommandException(String msg){
        super(msg);
    }
}