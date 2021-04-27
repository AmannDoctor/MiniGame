package controller;

import gameExceptions.*;
import model.ExitRoomDB;
import model.MapModel;

import java.io.IOException;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SQLiteDB;
/**Class: GameController
 * @author Mackinnon Jackson
 * @version 2.0
 *  Course: ITEC 3860 Spring 2021
 *  Written: 3/19/2021
 *
 *  This class –
 *  Will generate a mapModel and commands to run and return the command and their outputs respectively
 *  */

public class GameController {
    private Commands commands;
    private static MapModel map;

    /**Method GameController
     * Constructor for the GameController class
     * ----------------------------------------
     *  It will instantiate a new MapModel and Commands objects and calls
     *  the MapModel readItems and readRooms to build the map for the game.

     * */
    public GameController(){
        commands=new Commands();
        map=new MapModel();
        try {
            map.readItems();
            map.readRoom();
        }
        catch (IOException | IndexOutOfBoundsException e){
            e.printStackTrace();
        }


    }

    /** Method: executeCommand(String command, Room room)
     * Will execute the commands
     * @Param String command - the string version of the command
     * @Param Room room - The current room
     *
     * @result Will get the current command string and send it over to the Adenture
     *
     * @return String commandOutput;
     * @throws IOException
     */
    public String executeCommand(String command,Room room) throws IOException{
         if (!room.getVisited()) {
             room.setVisited(true);
         }
        String commandOutput="";
        try
        {
            commandOutput = commands.executeCommand(command, room);

        }
        catch(IOException e){
            commandOutput=e.getMessage();
        }
        return commandOutput;
    }


    public static MapModel getMap() throws IOException {
        if (map == null) {
           throw new IOException("The map model does not exist");
        }

        return map;
    }

    public Room retrieveByID(int curRoom) throws InvalidRoomException{
        if(map.getRoom(curRoom).equals(null)){
            throw new InvalidRoomException("The room that is located at the "+curRoom+ " position of the MapModel does not exist");
        }
        return map.getRoom(curRoom);
    }
    public String getRoomData(int roomNumber) throws SQLException, ClassNotFoundException, InvalidRoomException {
        Room rm = new Room();
        rm = rm.getRoom(roomNumber);
        return rm.toString();
    }

    /**
     * Method: getAllRoomsData
     * Purpose: Gets all rooms and returns an ArrayList<String> of all of the rooms
     * @return ArrayList<String>
     * @throws SQLException
     */
    public ArrayList<String> getAllRoomsData() throws SQLException, ClassNotFoundException, InvalidRoomException {
        ArrayList<Room> rooms = null;
        Room rm = new Room();
        rooms = rm.getAllRooms();
        ArrayList<String> roomStrs = new ArrayList<String>();
        for (Room room : rooms) {
            roomStrs.add(room.toString());
        }
        return roomStrs;
    }

    /**
     * Method: getMonsterData
     * Purpose: gets Monster data and returns a String containing it
     * @return String
     * @throws SQLException
     */
    public String getMonsterData(int monsterNumber) throws SQLException, ClassNotFoundException {
        Monster mon = new Monster();
        mon = mon.getMonster(monsterNumber);
        return mon.toString();
    }

    /**
     * Method: getAllMonstersData
     * Purpose: Get all of the monster data from the DB
     * @return ArrayList<String>
     * @throws SQLException
     */
    public ArrayList<String> getAllMonstersData() throws SQLException, ClassNotFoundException {
        Monster mon = new Monster();
        ArrayList<Monster> monsters = null;
        monsters = mon.getAllMonsters();
        ArrayList<String> monstersStr = new ArrayList<String>();
        for (Monster monster : monsters) {
            monstersStr.add(monster.toString());
        }
        return monstersStr;
    }

    /**
     * Method: getMonsterRoomData
     * Purpose: Gets the monster and room for the specified room and displays list of monsters in specified rooms
     * @return void
     *
     * @throws SQLException
     */
    public String getMonsterRoomData(int monsterRoomNumber) throws SQLException, ClassNotFoundException {
        MonsterRoom mr = new MonsterRoom();
        mr = mr.getMonster(monsterRoomNumber);
        return mr.toString();
    }

    /**
     * Method: getAllMonsterRoomData
     * Purpose: Get all of the monster and room data.  Joins these two tables.
     * @return ArrayList<String>
     * @throws SQLException
     */
    public ArrayList<String> getAllMonsterRoomData() throws SQLException, ClassNotFoundException {
        MonsterRoom mr = new MonsterRoom();
        ArrayList<MonsterRoom> monsterRooms = null;
        monsterRooms = mr.getAllMonsters();
        ArrayList<String> mrStrs = new ArrayList<String>();
        for (MonsterRoom monsterRoom : monsterRooms) {
            mrStrs.add(monsterRoom.toString());
        }
        return mrStrs;
    }

    public ArrayList<String> getAllItemsData() throws SQLException, ClassNotFoundException {
        Item i=new Item();
        ArrayList<Item> items = null;
        items = i.getAllItems();
        ArrayList<String> itemStr = new ArrayList<String>();
        for (Item item : items) {
            itemStr.add(item.toString());
        }
        return itemStr;
    }
    /**public ArrayList<String> getAllExitRoomData() throws SQLException, ClassNotFoundException{
        Exit er = new Exit();
        ArrayList<Exit> exitArrayList = null;
        exitArrayList = er.getAllExits();
        ArrayList<String> mrStrs = new ArrayList<String>();
        for (MonsterRoom monsterRoom : monsterRooms) {
            mrStrs.add(monsterRoom.toString());
        }
        return mrStrs;

    }*/




}