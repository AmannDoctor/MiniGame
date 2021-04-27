package controller;


import gameExceptions.*;
import model.ExitRoomDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**Class: Exit
 * @author Mackinnon Jackson
 * @version 2.0
 *  Course: ITEC 3860 Spring 2021
 *  Written: 3/19/2021
 *
 *  This class –
 *  Will act as the Rooms exits*/


public class Exit {
    private String direction;
    private int roomNum;
    private final List<String> VALID_DIRECTION= Arrays.asList("N","S","E","W","U","D","NORTH","SOUTH","EAST","WEST","UP","DOWN");

    public Exit(){
        ExitRoomDB erdb= new ExitRoomDB();
        try {
        roomNum= erdb.getNextExit();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }


    public String getDirection() {
        return direction;
    }

    public int getRoomNum() {
        return roomNum;
    }
    public void setRoomNum(int roomNum){
        this.roomNum=roomNum;
    }
    public void setDirection(String direction){
        this.direction=direction;
    }
    public String toString(){
        return direction + roomNum;
    }

    /** Method: buildExit(String ex)
     * Will build the exits based on the string
     *
     * @param ex -the string of exits that the method will read
     *
     * @result Will set the exits for the rooms
     *
     * @throws InvalidExitException
     */
    public void buildExit(String ex) throws InvalidExitException {
        String[] keyValue = ex.split(":");
        if((!VALID_DIRECTION.contains(keyValue[0]))){
            throw new InvalidExitException("This is not a valid exit");
        }
        setDirection(keyValue[0]);
        setRoomNum(Integer.valueOf(keyValue[1]));
    }


    public void buildExit(String direction,String roomNumber) throws InvalidExitException{
        if ((!VALID_DIRECTION.contains(direction.toUpperCase()))){
            throw new InvalidExitException("This is not a valid exit");
        }
        setDirection(direction);
        setRoomNum(Integer.valueOf(roomNumber));
    }

}