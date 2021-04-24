package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.GameController;
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
        int next = sdb.getMaxValue("roomNumber", "room") + 1;
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
        String sql = "Select * from Room WHERE roomNumber = " + id;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            System.out.println(rs.getInt("roomID"));
            rm.setRoomID(rs.getInt("roomID"));
            rm.setRoomName(rs.getString("roomName"));
            rm.setDescription(rs.getString("roomDescription"));

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
            rm.setRoomID(rs.getInt("roomNumber"));
            rm.setRoomName(rs.getString("roomName"));
            rm.setDescription(rs.getString("roomDescription"));

            rooms.add(rm);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return rooms;
    }
}