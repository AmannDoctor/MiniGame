package controller;

import model.MonsterRoomDB;
import model.PuzzleRoomDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class PuzzleRoom {
    private int puzzleNumber;
    private int puzzlePoints;
    private boolean Mandatory;
    private boolean Completed;
    private String Items;
    private String Solution;
    private int BookMarks;
    private int roomNumber;
    private String roomName;
    private String roomDescription;

    /**S
     * Method: getPuzzle
     * Purpose: Get the puzzle in a specific room
     * @param roomID
     * @return PuzzleROom
     * @throws SQLException
     */
    public PuzzleRoom getPuzzle(int roomID) throws SQLException, ClassNotFoundException {
        PuzzleRoomDB mdb = new PuzzleRoomDB();
        return mdb.getPuzzle(roomID);
    }

    /**
     * Method: getAllPuzzles
     * Purpose: gets all puzzles and their associated rooms
     * @return ArrayList<PuzzleRoom>
     * @throws SQLException
     */
    public ArrayList<PuzzleRoom> getAllPuzzles() throws SQLException, ClassNotFoundException {
        PuzzleRoomDB mdb = new PuzzleRoomDB();
        return mdb.getAllPuzzles();
    }

    /**
     * @return the puzzleNumber
     */
    public int getPuzzleNumber() {
        return puzzleNumber;
    }

    /**
     * @param puzzleNumber the puzzleNumber to set
     */
    public void setPuzzleNumber(int puzzleNumber) {
        this.puzzleNumber = puzzleNumber;
    }

    /**
     * @return the puzzlePoints
     */
    public int getPuzzlePoints() {
        return puzzlePoints;
    }

    /**
     * @param puzzlePoints the puzzlePoints to set
     */
    public void setPuzzlePoints(int puzzlePoints) {
        this.puzzlePoints = puzzlePoints;
    }

    /**
     * @return the mandatory
     */
    public boolean isMandatory() {
        return Mandatory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(boolean mandatory) {
        Mandatory = false;
    }

    /**
     * @return the completed
     */
    public boolean isCompleted() {
        return Completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(boolean completed) {
        Completed = false;
    }

    /**
     * @return the items
     */
    public String getItems() {
        return Items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(String items) {
        Items = items;
    }

    /**
     * @return the solution
     */
    public String getSolution() {
        return Solution;
    }

    /**
     * @param solution the solution to set
     */
    public void setSolution(String solution) {
        Solution = solution;
    }

    /**
     * @return the bookMarks
     */
    public int getBookMarks() {
        return BookMarks;
    }

    /**
     * @param bookMarks the bookMarks to set
     */
    public void setBookMarks(int bookMarks) {
        BookMarks = bookMarks;
    }

    /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the roomDescription
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * @param roomDescription the roomDescription to set
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    @Override
    public String toString() {
        return "PuzzleRoom [puzzleNumber=" + puzzleNumber + ", puzzlePoints=" + puzzlePoints + ", Mandatory="
                + Mandatory + ", Completed=" + Completed + ", Items=" + Items + ", Solution=" + Solution
                + ", BookMarks=" + BookMarks + ", roomNumber=" + roomNumber + ", roomName=" + roomName
                + ", roomDescription=" + roomDescription + "]";
    }
}