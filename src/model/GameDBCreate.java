package model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;

import controller.GameController;

/**
 * Class : GameDBCreate
 * @author: Rick Price
 * @version: 1.0
 * Course:
 * Written: March 29, 2021
 * This class creates the Game db if it doesn't exist
 * Purpose: Allows the Game test program to create a DB if not there
 */
public class GameDBCreate {
    SQLiteDB sdb;

    /**
     * Method: buildTables
     * Purpose: Build all tables
     * @return void
     * @throws SQLException
     */
    public void buildTables() throws SQLException, ClassNotFoundException {
        buildRoom();
        buildMonster();
        buildMonsterRoom();

    }

    /**
     * Method: buildRoom
     * Purpose: Build the Room table and load data
     * @return void
     * @throws SQLException
     */
    public void buildRoom() throws SQLException, ClassNotFoundException {
        sdb =  new SQLiteDB();

        FileReader fr;
        try {
            fr = new FileReader("Rooms.txt");
            Scanner inFile = new Scanner(fr);
            while (inFile.hasNextLine()) {
                String sql = inFile.nextLine();
                sdb.updateDB(sql);
            }
            inFile.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
    }

    /**
     * Method: buildMonster
     * Purpose: Build the Monster table and load data
     * @return void
     * @throws SQLException
     */
    public void buildMonster() throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB();

        String sql = "CREATE TABLE Monster(monsterNumber int Primary Key not Null, monsterName text not null, monsterDescription text not null, hitPoints int not null, "
                + "minDamage int not null, maxDamage int not null)";
        sdb.updateDB(sql);
        sql = "INSERT INTO Monster(monsterNumber, monsterName, monsterDescription, hitPoints, minDamage, maxDamage) "
                + " Values(1, 'Giant Rat', 'A large snarling rat with an extremely long pointed and tooth filled snout', "
                + "10, 3, 10)";
        sdb.updateDB(sql);
        sql = "INSERT INTO Monster(monsterNumber, monsterName, monsterDescription, hitPoints, minDamage, maxDamage) "
                + " Values(2, 'Red Dragon', 'A huge red dragon breathing fire', "
                + "100, 10, 40)";
        sdb.updateDB(sql);
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
    }

    /**
     * Method: buildMonsterRoom
     * Purpose: Build the Monster table and load data
     * @return void
     * @throws SQLException
     */
    public void buildMonsterRoom() throws SQLException, ClassNotFoundException {
        sdb = new SQLiteDB();
        String sql = "CREATE TABLE MonsterRoom(monsterNumber int not Null, roomNumber int not Null)";
        sdb.updateDB(sql);
        sql = "INSERT INTO MonsterRoom(monsterNumber, roomNumber) Values(1, 2)";
        sdb.updateDB(sql);
        sql = "INSERT INTO MonsterRoom(monsterNumber, roomNumber) Values(2, 2)";
        sdb.updateDB(sql);
        sql = "INSERT INTO MonsterRoom(monsterNumber, roomNumber) Values(2, 3)";
        sdb.updateDB(sql);
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
    }
}