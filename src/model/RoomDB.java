package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Exit;
import controller.GameController;
import controller.Item;
import controller.Room;
import gameExceptions.InvalidRoomException;

/**
 * Class : RoomDB.java
 * @author: Rick Price
 * @version: 1.0
 * Course: ITEC 3860
 * Written: March 29, 2021
 * This class handles all of the DB interactions for Rooms
 */
public class RoomDB {
    /**
     * Method: getNextRoomID
     * Purpose: gets the next ID for a room
     * @return int
     */
    public int getNextRoomID() throws SQLException {
        SQLiteDB sdb = null;
        try {
            sdb = new SQLiteDB();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int next = sdb.getMaxValue("roomID", "room") + 1;

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();

        return next;
    }

    /**
     * Method: getRoom
     * Purpose: Gets a room based upon the supplied ID
     * @param id
     * @return Room
     * @throws SQLException
     */
    public Room getRoom(int id) throws SQLException, ClassNotFoundException, InvalidRoomException {
        SQLiteDB sdb = new SQLiteDB();
        Room rm = new Room();
        String sql = "Select * from Room WHERE roomID = " + id;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {

            rm.setRoomID(rs.getInt("roomID"));
            rm.setRoomName(rs.getString("Name"));
            rm.setDescription(rs.getString("Description"));
            rm.setVisited(rs.getBoolean("visited"));
        }
        else {
            throw new SQLException("Room " + id + " not found");
        }
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return rm;
    }

    /**
     * Method: getAllRooms
     * Purpose: gets all rooms
     * @return ArrayList<Room>
     * @throws SQLException
     */
    public ArrayList<Room> getAllRooms() throws SQLException, ClassNotFoundException, InvalidRoomException {
        ArrayList<Room> rooms = new ArrayList<Room>();
        SQLiteDB sdb = new SQLiteDB();
        String sql = "Select * from Room";

        ResultSet rs = sdb.queryDB(sql);

        while (rs.next()) {
            Room rm = new Room();
            rm.setRoomID(rs.getInt("roomID"));
            rm.setRoomName(rs.getString("Name"));
            rm.setDescription(rs.getString("Description"));
            rm.setExits(getExits(rm.getRoomID()));
            ArrayList<Item> x=new ArrayList<>();
            rm.setRoomItems(x);
            rooms.add(rm);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return rooms;
    }
    public ArrayList<Exit> getExits(int roomID) throws SQLException,ClassNotFoundException{
        ArrayList<Exit> exits=new ArrayList<>();
        String sql = "Select * from Exits WHERE RoomID = " + roomID;
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            Exit exit=new Exit();
            System.out.println(rs.getInt("RoomNum"));
            exit.setRoomNum((rs.getInt("RoomNum")));
            exit.setDirection((rs.getString("Direction")));



            exits.add(exit);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return exits;

    }
}