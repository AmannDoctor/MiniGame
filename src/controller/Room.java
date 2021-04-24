package controller;

import gameExceptions.InvalidRoomException;
import model.MapModel;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import model.RoomDB;

/**Class: Room
     * @author Mackinnon Jackson
     * @version 2.0
     *  Course: ITEC 3860 Spring 2021
     *  Written: February 20, 2021
     *
     *  This class – The room class is a basic object that will format a room object. It is able to send over the
 *  available directions and the description of the current room the player is in.   */

    public class Room {
        private int roomID;
        private boolean visited;
        private ArrayList<Exit> exits;
        private MapModel mapModel;
        private String Description;
        private String roomName;
        private ArrayList<Item> roomItems;
        private Monster monster;
        private Puzzle puzzle;
        private Help help;
        private Hints hints;


    /**
     *Method Room
     *Room class Constructor
     *Initializes the ArrayLists exits and items as well as gets the current room.*/
    public Room(){
        roomItems = new ArrayList<>();
        exits= new ArrayList<>();
        try {
            mapModel = GameController.getMap();
        }catch(IOException e){
            e.getStackTrace();
        }
        }

    /**Method Room
     * Room class Constructor
     * @param roomID - Current room ID.
     * @throws InvalidRoomException
     */
    public Room(int roomID) throws InvalidRoomException{

            this();
            retrieveByID(roomID);

        }
        public String getDescription(){
            return Description;
        }

    public ArrayList<Exit> getExits() {
        return exits;
    }


    public void setDescription(String description) throws InvalidRoomException{
    String line="";
        String[] roomDiscArray = description.split("\\. ");
        for (String l:roomDiscArray) {line=line+l+"."+"\n";}
        Description = line;
    }

    public void setExits(ArrayList<Exit> exits){
            this.exits=exits;
        }
    public void setRoomID(int roomID){
            this.roomID = roomID;
        }
    public void setRoomItems(ArrayList<Item> roomItems) {
        this.roomItems = roomItems;
    }
    public void setRoomName(String roomName) throws InvalidRoomException{
        this.roomName = roomName;
    }


    public void removeItem(Item item) throws InvalidRoomException{
            roomItems.remove(item);
        }


        public void dropItem(Item item) throws InvalidRoomException{
            roomItems.add(item);

        }

        public int getRoomID() {
            return roomID;
        }
        public boolean getVisited() {
            return visited;
        }
        public void setVisited(boolean visited) {
            this.visited = visited;
        }
        public String getRoomName(){return roomName;}




    /**Method: display()
     *
     * Builds a String representation of the current room
     *
     * @result Calls buildItems, buildDescription, and displayExits to build this String
     *
     *  @return String - the current room display String */
        public String display() throws InvalidRoomException{

            return buildDescription() + displayExits() + buildItems();
        }

        public ArrayList<Item> getRoomItems() {
            return roomItems;
        }



        public String toString(){
        return "[RoomNumber) roomName: " + getRoomID()+")"+ getRoomName()+"]\n" +"visited status: ["+
                getVisited()+"]\n"+"Room Description: ["+getDescription()+"]\n------------------------\n"+
                "room directions: [" +buildExits()+"\n[" +roomItems.toString()+ "]\n---------------\n";
        }



    /**Method: buildDescription()
     *
     * Will generate the description to be sent based on if the room has been visited and the rooms description
     *
     * @result Builds a String of the description
     *
     *  @return String - Description of the room */

        private String buildDescription(){
            String roomDescripton="";
            if(visited){return "You have already visited the "+roomName+" room before.\n";}
            else{roomDescripton="You have not visited the room "+roomName+" yet.\n";}
            roomDescripton=roomDescripton+"\n"+roomName+" Description: ";
            roomDescripton=roomDescripton+getDescription()+"\n";

            return roomDescripton;
        }


    /**Method: buildExits()
     * Will generate a sting for the exits to be return
     *
     * @result Builds a String of the exits in the room for the toString
     *
     *  @return String - the current room exits text */


        private String buildExits() {

            String exitString="";
            for (Exit exit : exits) {
                exitString=exitString+exit.toString()+" ";
            }
            return exitString;
        }


    /**Method: buildItems()
     * Builds a String of the items in the room
     *
     *  @result An String based off the room items ArrayList
     * @return String - the current room items text
     *
     *  @throws InvalidRoomException*/


        private String buildItems() throws InvalidRoomException{
            String itemString="There are no items in this room";
            if(!roomItems.isEmpty()){
                itemString="\nThere are currently "+roomItems.size()+" item(s) in the "+roomName+" room: \n";
                for(Item item:roomItems) {
                    itemString = itemString + item.getName()+" ";;
                }
            }
            return itemString;
        }
    /**Method: displayExits()
     * Will generate a sting for the exits to be return to the main display
     *
     * @result Builds a String of the exits in the room
     *
     *  @return String - the current room exits text*/
        private String displayExits() {

            HashMap<String, String> directionsNames = new HashMap<>();
            directionsNames.put("N","North");directionsNames.put("S","South");
            directionsNames.put("E","East");directionsNames.put("W","West");

            String nextRoomDirections="---------------------\nHere is the current directions that you can go: \n\n";
            for (Exit exit : exits) {
                if (exit.getRoomNum()>0){
                   nextRoomDirections=nextRoomDirections+directionsNames.get(exit.getDirection()) + " ";
                }
            }



            return nextRoomDirections + "\n\n";


        }

    /**Method: updateRoom()
     * Will update the current room infomation
     *
     *  @result Calls MapModel updateRoom(this) to save the current room in the map
     *
     *  @throws InvalidRoomException*/

        public void updateRoom() throws InvalidRoomException {
            mapModel.updateRoom(this);
        }


    /**Method: retrieveByID(int roomNum)
     * Will copy all the rooms content over from the mapModel to the current room
     *
     * @param roomNumber - The current room Integer that needs to be retrieved
     *
     *  @result Retrieves the requested Room from MapModel.
     *
     *  @return Room - the requested Room
     *
     *  @throws InvalidRoomException*/
        public Room retrieveByID(int roomNumber) throws InvalidRoomException{
            if(mapModel.getRoom(roomNumber).equals(null)){
                throw new InvalidRoomException("The room that you are looking for does not exits");
            }
            Room rm=mapModel.getRoom(roomNumber);
            roomName=rm.getRoomName();
            roomID = rm.getRoomID();
            roomItems= rm.getRoomItems();
            exits=rm.getExits();
            Description= rm.getDescription();
            visited= rm.getVisited();
            mapModel= rm.mapModel;

            return rm;

        }



    /**Method: validDirection(Char cmd)
     * Determines if the direction entered by the user is valid for this room
     *
     *  @result gets the next rooms roomID if it is a valid directions
     *
     *  @return int - the ID of the destination room
     *
     *  @throws InvalidRoomException*/
        public int validDirection(char cmd) throws InvalidRoomException{
            List<Character> directions= Arrays.asList('N', 'S', 'E', 'W');
            if(!directions.contains(cmd)){
                throw new InvalidRoomException("That is not a valid direction");
            }
            int roomID=0;
            for(Exit exit : exits){
                if(exit.getDirection().charAt(0)==cmd){
                    roomID= exit.getRoomNum();
                }
            }

            return roomID;
        }


    public Hints getHints() {
        return hints;
    }

    public Help getHelp() {
        return help;
    }

    public Monster getMonster() {
        return monster;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
    public Room getRoom(int id) throws SQLException, ClassNotFoundException, InvalidRoomException {
        RoomDB rdb = new RoomDB();
        return rdb.getRoom(id);
    }

    public ArrayList<Room> getAllRooms() throws SQLException, ClassNotFoundException, InvalidRoomException {
        RoomDB rdb = new RoomDB();
        return rdb.getAllRooms();
    }

}
