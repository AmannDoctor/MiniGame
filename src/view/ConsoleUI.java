package view;

import controller.GameController;
import controller.Room;
import gameExceptions.InvalidRoomException;

import java.io.IOException;
import java.util.*;
import java.sql.SQLException;

public class ConsoleUI {
    private GameController gc;
    private final int LAST_CHOICE = 6;
    private Scanner input;

    /**
     * Method: startGame
     * Purpose: handles the demo
     * @return void
     */
    private void playGame(){
        Room r=new Room();
        try {r=r.getRoom(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InvalidRoomException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

        while (!playersCommand.equalsIgnoreCase("x")) {
            try {
                System.out.println(r.display());
                System.out.println("Please enter a command down below:");
                playersCommand = getCommand();
                System.out.println(gc.executeCommand(playersCommand, r));



            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public String getCommand() {
        return input.nextLine().toUpperCase();
    }

    public void startGame() {
        gc = new GameController();
        boolean again = true;
        input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Select the option you would like to execute");
            System.out.println("1. Get all rooms");
            System.out.println("2. Start Game");
            System.out.println("3. Show all Monster data");
            System.out.println("4. Show a specific monster");
            System.out.println("5. Show the rooms where monsters are programmed.");
            System.out.println(LAST_CHOICE + ". Exit the game");

            try {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        printStrs(getAllRooms());
                        break;
                    case 2:
                            playGame();
                        break;
                    case 3:
                        printStrs(getAllMonsters());
                        break;
                    case 4:
                        try {
                            System.out.println("What monster would you like to retrieve?");
                            int monster = input.nextInt();
                            System.out.println(getMonster(monster));
                        }
                        catch (SQLException sqe) {
                            System.out.println(sqe.getMessage());
                        }
                        break;
                    case 5:
                        printStrs(getMonsterRoom());
                        break;
                    case 6:
                        again = false;
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("You must enter an integer for your choice");
            }
            catch (SQLException | ClassNotFoundException e) {
                System.out.println("An error occurred while reading data. " + e.getMessage());
                System.out.println("Exiting the program.");
                again = false;
            } catch (InvalidRoomException e) {
                e.printStackTrace();
            } finally {
                input.nextLine();
            }

        } while (again);

        input.close();
    }

    /**
     * Method: getAllRooms
     * Purpose: Retreieve all rooms from the database and return them as a List<String>
     * @param: None
     * @return: ArrayList<String>
     */
    private ArrayList<String> getAllRooms() throws SQLException, ClassNotFoundException, InvalidRoomException {
        return gc.getAllRoomsData();
    }

    /**
     * Method: getRoom
     * Purpose: Retreieve the room from the database and return it as a String
     * @param: int room number
     * @return: String
     */
    private String getRoom(int room) throws SQLException, ClassNotFoundException, InvalidRoomException {
        return gc.getRoomData(room);
    }

    /**
     * Method: getAllMonsters
     * Purpose: Retreieve all monsters from the database and return them as a List<String>
     * @param: None
     * @return: ArrayList<String>
     */
    private ArrayList<String> getAllMonsters() throws SQLException, ClassNotFoundException {
        return gc.getAllMonstersData();
    }

    /**
     * Method: getMonster
     * Purpose: Retreieve the monster from the database and return it as a String
     * @param: int room number
     * @return: String
     */
    private String getMonster(int monster) throws SQLException, ClassNotFoundException {
        return gc.getMonsterData(monster);
    }

    /**
     * Method: getMonsterRooms
     * Purpose: Retreieve all monsters and their associated rooms from the database and return them as a List<String>
     * @param: None
     * @return: ArrayList<String>
     */
    private ArrayList<String> getMonsterRoom(){
        ArrayList<String> monRoom = new ArrayList<>();
        try {
            monRoom = gc.getAllMonsterRoomData();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return monRoom;
    }

    /**
     * Method: printStrs
     * Purpose: Print the ArrayList of Strings
     * @param strs void
     * @return void
     */
    private void printStrs(ArrayList<String> strs) {
        for (String str : strs) {
            System.out.println(str);
        }
    }

}