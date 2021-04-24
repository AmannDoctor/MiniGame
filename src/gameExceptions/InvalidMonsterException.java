package gameExceptions;

import java.io.IOException;

public class InvalidMonsterException extends IOException {
    public InvalidMonsterException(){

    }
    public InvalidMonsterException(String msg){

        super(msg);
    }
}