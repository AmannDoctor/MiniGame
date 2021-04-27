package view;


import controller.*;
import gameExceptions.InvalidExitException;


import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
/** Class: Adventure
 *Creator: Mackinnon Jackson
 *version 2.0
 *Course: ITEC 3860 Spring 2021
 *Written: 3/19/2021
 *
 * This class -
 * Will start the GameController and initialize a new game with the program runs
 */

public class Adventure {
    private Scanner input;
    private static Room room;
    private int current=0;
    private GameController gc;

    /** Method: Adventure
     * Constructor for the Adventure class
     * Creates an instance of the GameController class which loads the data for the game.
     */
    public Adventure(){
        gc=new GameController();
    }


    public static void setRoom(Room newRoom){
        room=newRoom;
    }

    public String getCommand(){
        return input.nextLine().toUpperCase();
    }


    /** Method: playGame()
     * Allows the player to play the game. Prints an introduction message Loops until the user chooses to exit.
     * Prints the current rooms display text if the direction is valid.
     * If an invalid direction is entered, catches the exception and prints the message in that exception. Calls
     * getCommand to get users input.
     *
     * @result Will play the games
     *
     * @throws InvalidExitException
     */
    private void playGame(){

        try{
            System.out.println(GameController.getMap().getMap());
            room= gc.retrieveByID(0);}

        catch (IOException e){

            System.out.println(e);
        }

        System.out.println("""
                Welcome to the Dungeon Finder! Once you start, you will have your trusty compass to help guide you through the maze. 
                When you see an open pathway (See under the "Available Passage Ways" section),
                just use the first letter of the direction or simply the whole word and you will move to that location! 
              
                You mainly have four directional options: North, South, East, and West.
                To look at a previous room, just enter in "Look" (or L).
                
                To interact with an object in your inventory or in the room, the commands are:
                
                1) "get <item full name (w/ spaces)>" (Or G) if you want to get an item in a room to your backpack
                2) "drop <item full name (w/ spaces)>" (Or D) if you want to drop an item in a room from your backpack
                3) "inspect <item full name (w/ spaces)>" (or I) if you want to look at an item
                4) "backpack" (or B) if you want to see what is in your inventory
                
                
                If you want to leave, just enter the letter X and you will exit out of the game!
                ------------------------
                """);

        String playersCommand="";
        try{
        System.out.println(gc.getAllItemsData());}
         catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (!playersCommand.equalsIgnoreCase("x")) {
                try {
                    System.out.println(room.display());
                    System.out.println("Please enter a command down below:");
                    playersCommand = getCommand();
                    System.out.println(gc.executeCommand(playersCommand, room));


                }
                catch(IOException e){
                System.out.println(e.getMessage());
            }
        }

    }


    public static void main(String[] args) {
        Adventure adventure=new Adventure();
        adventure.input = new Scanner(System.in);

        adventure.playGame();
        adventure.input.close();

    }

}