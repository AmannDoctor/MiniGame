package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.MonsterRoomDB;

/**
 * Class : MonsterRoom.java
 * @author: Rick Price
 * @version: 1.0
 * Course: ITEC 3860
 * Written: March 29, 2021
 * Purpose: Provide the logic for the association table mapping monsters to rooms.
 */
public class MonsterRoom {
    private int monsterNumber;
    private String monsterName;
    private String monsterDescription;
    private int hitPoints;
    private int maxDamage;
    private int minDamage;
    private int roomNumber;
    private String roomName;
    private String roomDescription;

    /**
     * Method: getMonster
     * Purpose: Get the monster in a specific room
     * @param roomID
     * @return MonsterROom
     * @throws SQLException
     */
    public MonsterRoom getMonster(int roomID) throws SQLException, ClassNotFoundException {
        MonsterRoomDB mdb = new MonsterRoomDB();
        return mdb.getMonster(roomID);
    }

    /**
     * Method: getAllMonsters
     * Purpose: gets all mosters and their associated rooms
     * @return ArrayList<MonsterRoom>
     * @throws SQLException
     */
    public ArrayList<MonsterRoom> getAllMonsters() throws SQLException, ClassNotFoundException {
        MonsterRoomDB mdb = new MonsterRoomDB();
        return mdb.getAllMonsters();
    }

    /**
     * Method: getMonsterNumber
     * @return the monsterNumber
     */
    public int getMonsterNumber() {
        return monsterNumber;
    }

    /**
     * Method: setMonsterNumber
     * @param monsterNumber the monsterNumber to set
     */
    public void setMonsterNumber(int monsterNumber) {
        this.monsterNumber = monsterNumber;
    }

    /**
     * Method: getMonsterName
     * @return the monsterName
     */
    public String getMonsterName() {
        return monsterName;
    }

    /**
     * Method: setMonsterName
     * @param monsterName the monsterName to set
     */
    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    /**
     * Method: getMonsterDescription
     * @return the monsterDescription
     */
    public String getMonsterDescription() {
        return monsterDescription;
    }

    /**
     * Method: setMonsterDescription
     * @param monsterDescription the monsterDescription to set
     */
    public void setMonsterDescription(String monsterDescription) {
        this.monsterDescription = monsterDescription;
    }

    /**
     * Method: getHitPoints
     * @return the hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Method: setHitPoints
     * @param hitPoints the hitPoints to set
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Method: getMaxDamage
     * @return the maxDamage
     */
    public int getMaxDamage() {
        return maxDamage;
    }

    /**
     * Method: setMaxDamage
     * @param maxDamage the maxDamage to set
     */
    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    /**
     * Method: getMinDamage
     * @return the minDamage
     */
    public int getMinDamage() {
        return minDamage;
    }

    /**
     * Method: setMinDamage
     * @param minDamage the minDamage to set
     */
    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    /**
     * Method: getRoomNumber
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Method: setRoomNumber
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Method: getRoomName
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Method: setRoomName
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Method: getRoomDescription
     * @return the roomDescription
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Method: setRoomDescription
     * @param roomDescription the roomDescription to set
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    /**
     * Method: toString
     * Purpose: Returns a String representation of the MonsterRoom data.
     * @return String
     */
    @Override
    public String toString() {
        return "MonsterRoom,  monsterNumber = " + monsterNumber + ", monsterName = " + monsterName + "\n monsterDescription = "
                + monsterDescription + "\nwith hitPoints = " + hitPoints + " causing maxDamage = " + maxDamage + " and minDamage = "
                + minDamage + ".\nIs in roomNumber = " + roomNumber + ", roomName = " + roomName + ", roomDescription = "
                + roomDescription;
    }
}
