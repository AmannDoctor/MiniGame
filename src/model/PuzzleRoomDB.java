package model;

import controller.MonsterRoom;
import controller.PuzzleRoom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuzzleRoomDB {
    /**
     * Method: getPuzzle
     * Purpose: get the puzzle from a specific room
     * @param roomID
     * @return PuzzleRoom
     * @throws SQLException
     */
    public PuzzleRoom getPuzzle(int roomID) throws SQLException, ClassNotFoundException {
        PuzzleRoom pr = new PuzzleRoom();
        String sql = "SELECT DISTINCT p.puzzleNumber, p.puzzlePoints, p.Mandatory, p.Completed, p.Items, "
                + " p.Solution, p.BookMarks, r.roomNumber, r.roomName, r.roomDescription FROM Puzzle p, room r" +
                " INNER JOIN PuzzleRoom pr on pr.puzzleNumber = p.puzzleNumber where r.roomNumber = " + roomID + " ORDER BY pr.puzzleNumber";
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            pr.setPuzzleNumber(rs.getInt("puzzleNumber"));
            pr.setPuzzlePoints(rs.getInt("puzzlePoints"));
            pr.setMandatory(rs.getBoolean("Mandatory"));
            pr.setCompleted(rs.getBoolean("Completed"));
            pr.setItems(rs.getString("Items"));
            pr.setSolution(rs.getString("Solution"));
            pr.setBookMarks(rs.getInt("Bookmarks"));
            pr.setRoomNumber(rs.getInt("roomNumber"));
            pr.setRoomName(rs.getString("roomName"));
            pr.setRoomDescription(rs.getString("roomDescription"));
        }
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return pr;
    }

    /**
     * Method: getAllPuzzles
     * Purpose: get the list of all puzzles and their rooms
     * @return ArrayList<Puzzle>
     * @throws SQLException
     */
    public ArrayList<PuzzleRoom> getAllPuzzles() throws SQLException, ClassNotFoundException {
        ArrayList<PuzzleRoom> puzzleRooms = new ArrayList<PuzzleRoom>();
        String sql = "SELECT DISTINCT Puzzle.puzzleNumber, Puzzle.puzzlePoints, Puzzle.Mandatory, Puzzle.Completed, "
                + " Puzzle.Items, Puzzle.Solution, Puzzle.BookMarks, Room.roomNumber, Room.roomName, Room.roomDescription FROM PuzzleRoom " +
                "INNER JOIN Room ON PuzzleRoom.roomNumber = Room.roomNumber " +
                " INNER JOIN Puzzle on PuzzleRoom.puzzleNumber = Puzzle.puzzleNumber";
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            PuzzleRoom pr = new PuzzleRoom();
            pr.setPuzzleNumber(rs.getInt("puzzleNumber"));
            pr.setPuzzlePoints(rs.getInt("puzzlePoints"));
            pr.setMandatory(rs.getBoolean("Mandatory"));
            pr.setCompleted(rs.getBoolean("Completed"));
            pr.setItems(rs.getString("Items"));
            pr.setSolution(rs.getString("Solution"));
            pr.setBookMarks(rs.getInt("Bookmarks"));
            pr.setRoomNumber(rs.getInt("roomNumber"));
            pr.setRoomName(rs.getString("roomName"));
            pr.setRoomDescription(rs.getString("roomDescription"));
            puzzleRooms.add(pr);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return puzzleRooms;
    }
}