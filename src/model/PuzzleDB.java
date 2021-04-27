package model;

import controller.Puzzle;
import controller.Room;
import gameExceptions.InvalidRoomException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuzzleDB {
    public int getNextPuzzleID() throws SQLException {
        SQLiteDB sdb = null;
        try {
            sdb = new SQLiteDB();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int next = sdb.getMaxValue("PuzzleID", "Puzzle") + 1;
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
    public Puzzle getPuzzle(int id) throws SQLException, ClassNotFoundException, InvalidRoomException {
        SQLiteDB sdb = new SQLiteDB();
        Puzzle p=new Puzzle();
        String sql = "Select * from Puzzle WHERE PuzzleID = " + id;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {

            p.setID(rs.getInt("PuzzleID"));
            p.setPoints(rs.getInt("Points"));

            p.setMandatory(rs.getBoolean("Mandatory"));
            p.setCompleted(false);

        }
        else {
            throw new SQLException("Puzzle " + id + " not found");
        }
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return p;
    }

    /**
     * Method: getAllRooms
     * Purpose: gets all rooms
     * @return ArrayList<Room>
     * @throws SQLException
     */
    public ArrayList<Puzzle> getAllPuzzles() throws SQLException, ClassNotFoundException, InvalidRoomException {
        ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();
        SQLiteDB sdb = new SQLiteDB();
        String sql = "Select * from Puzzle";

        ResultSet rs = sdb.queryDB(sql);

        while (rs.next()) {
            Puzzle p=new Puzzle();
            p.setID(rs.getInt("PuzzleID"));
            p.setPoints(rs.getInt("Points"));

            p.setMandatory(rs.getBoolean("Mandatory"));
            p.setCompleted(false);

            puzzles.add(p);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return puzzles;
    }
}