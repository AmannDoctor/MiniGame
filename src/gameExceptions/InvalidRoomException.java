package gameExceptions;

import java.io.IOException;

public class InvalidRoomException extends IOException {
    public InvalidRoomException(){
        super("There seems to be an error with the rooms");

    }
    public InvalidRoomException(String msg){
        super(msg);
    }
}