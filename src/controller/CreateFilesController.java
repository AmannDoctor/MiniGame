package controller;

import java.sql.SQLException;

import gameExceptions.GameException;
import model.GameDBCreate;

/**
 * Class : CreateFilesController.java
 * @author: Rick Price
 * @version: 1.0
 * Course: ITEC 3860
 * Written: March 29, 2021
 * This class calls into the model to build the database if it doesn't exist.
 */
public class CreateFilesController {

    /**
     * Method: createFile
     * Purpose: Create the database for GameDB
     * @return void
     */
    public void createFile() throws GameException {
        try {
            GameDBCreate sdb = new GameDBCreate();
            sdb.buildTables();
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new GameException("Creation failed");
        }
    }
}
