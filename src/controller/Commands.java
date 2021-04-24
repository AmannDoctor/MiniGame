package controller;

import gameExceptions.*;

import view.Adventure;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**Class: Commands
 * @author Mackinnon Jackson
 * @version 2.0
 *  Course: ITEC 3860 Spring 2021
 *  Written: 3/19/2021
 *
 *  This class –
 *  Will format and run the commands as well as instantiates a new player object for tracking inventory in the game */

public class Commands {

    private Player player=new Player();

    private String prevRoomDirec=new String();

    public static final List<Character> VALID_DIRECTIONS = Arrays.asList('N', 'S', 'E', 'W'),
            ITEM_COMMANDS= Arrays.asList('G','D','I','U','E'),OVERALL_COMMANDS=Arrays.asList('E','S','G','D');
    public static final Character HELP_COMMAND='H';
    public static final List<String> FIGHT_COMMANDS = Arrays.asList("FIGHT","DEFEND","RUN");
    public static final int EXIT_COMMAND=7;

    public Commands(){

    }

    /** Method: executeCommand(String command, Room room)
     * Will execute the commands
     *
     * @param cmd - the string version of the command
     *
     * @param room - The current room of the player
     *
     * @result String  will return the final string from the executed commands
     * @throws IOException
     */



    public String executeCommand(String cmd,Room room) throws IOException {
        String stringOutput="";
        int validateCommand=0;
        try {validateCommand = validateCommand(cmd);}
        catch(InvalidCommandException e){stringOutput=e.getMessage();}

        if (validateCommand==1) {
            try{
                stringOutput = itemCommand(cmd, room);
            }
            catch(InvalidCommandException | InvalidRoomException e){
                stringOutput=e.getMessage();
            }
        }
        else if (validateCommand==2) {
            try{

            stringOutput= move(cmd,room);
            }
            catch (IOException e){
                stringOutput=e.toString();
            }
        }
        else if (validateCommand==3) {
            stringOutput=room.display();
        }
        else if (validateCommand==4) {stringOutput= player.printInventory();}
        else if (validateCommand==5){stringOutput=fightCmds(cmd, room);}
        else if (validateCommand==7) {
            stringOutput = "Thank you for playing my game!";
        }
        return stringOutput;
    }
    /** Method: lookItem(String command, Room room)
     * Will input the cmd and runs the display command
     *
     * @param cmd - the string version of the command
     *
     * @param room - The current room of the player
     *
     * @result String outputCommand - will return the final string from getting the .display from the designated rooms.
     *
     * @thows IOException
     */

    public String lookItem(String cmd,Room room) throws IOException{
        String[] ItemNameGrabber=cmd.split(" ");
        String stringOutput="";
        boolean itemDoesNotExist=true;
        for (int i = 1; i < ItemNameGrabber.length; i++) {
            stringOutput = stringOutput + ItemNameGrabber[i] + " ";
        }
        stringOutput=stringOutput.trim();
        try {
            for (Item i : room.getRoomItems()) {
                if (i.getName().equalsIgnoreCase(stringOutput)) {
                    stringOutput = i.display();
                    itemDoesNotExist = false;
                }
            }
            for (Item i : player.getInventory()) {
                if (i.getName().equalsIgnoreCase(stringOutput)) {
                    stringOutput = i.display();
                    itemDoesNotExist = false;
                }
            }
        }
        catch (InvalidItemException e){
            stringOutput=e.toString();
        }
        if(itemDoesNotExist){
            throw new IOException("This item does not exist to be inspected");
        }
        return stringOutput;
    }


    /** Method: useItem(String command, Room room)
      * @param cmd - the string version of the command

     * @param room - The current room of the player

     * @result String outputCommand - will return the final string from getting the .display from the designated rooms.

     * @thows IOException
     */

    public String useItem(String cmd, Room room) throws IOException{
        String[] ing=cmd.split(" ");
        String stringOutput="";
        boolean itemDoesNotExist=true;
        for (int i = 1; i < ing.length; i++) {
            stringOutput = stringOutput + ing[i] + " ";
        }
        stringOutput=stringOutput.trim();

        try {
            for (Item i : player.getInventory()) {
                if (i.getName().equalsIgnoreCase(stringOutput)) {

                    /**Fill in with use counter*/


                    itemDoesNotExist = false;
                }
                else{
                    throw new InvalidItemException();
                }
            }
        }
        catch (InvalidItemException e){
            stringOutput=e.toString();
        }
        if(itemDoesNotExist){
            throw new IOException("This item does not exist to be inspected");
        }
        return stringOutput;
    }
    /** Method: useItem(String command, Room room)
     * @param cmd - the string version of the command

     * @param room - The current room of the player

     * @result String outputCommand - will return the final string from getting the .display from the designated rooms.

     * @thows IOException
     */

    public String equipItem(String cmd, Room room) throws IOException{

       String stringOutput=commandTrimmer(cmd);
        boolean itemDoesNotExist=true;
        try {
            for (Item i : player.getInventory()) {
                if (i.getName().equalsIgnoreCase(stringOutput)&&i.isEquipable()) {

                    /**Fill in with Equip Code*/


                    itemDoesNotExist = false;
                }
                else{
                    throw new InvalidItemException();
                }
            }
        }
        catch (InvalidItemException e){
            stringOutput=e.toString();
        }
        if(itemDoesNotExist){
            throw new IOException("This item does not exist to be inspected");
        }
        return stringOutput;
    }

    public String commandTrimmer(String cmd){

        String[] ing=cmd.split(" ");
        String stringOutput="";

        for (int i = 1; i < ing.length; i++) {
            stringOutput = stringOutput + ing[i] + " ";
        }

        return stringOutput.trim();
    }


    /** Method: validateCommand(String cmdLine)
     * Will validate the commands giving in the cmd land
     *
     * @param cmdLine - the string version of the command
     *
     * @result Returns an int that tells what type of command ( 1 for item commands, 2 for movement, 3 for Look,
     * 4 for Backpack, EXIT_COMMAND for exit, or throws an exception for an invalid command)
     *
     * @return Int commandValidation
     *
     * @throws InvalidCommandException
     */
    private int validateCommand(String cmdLine) throws InvalidCommandException{
        String[] commandGrabber=cmdLine.split(" ",2);

    int commandInt=0;
    if (commandGrabber[0].equalsIgnoreCase("go")&&VALID_DIRECTIONS.contains(commandGrabber[1].charAt(0))) {
        commandInt=2;
    }else if (ITEM_COMMANDS.contains(cmdLine.charAt(0))&&commandGrabber.length>1) {
        commandInt=1;
    } else if (cmdLine.equalsIgnoreCase("b") || cmdLine.equalsIgnoreCase("backpack")) {
        commandInt=4;
    } else if (cmdLine.equalsIgnoreCase("l")|| commandGrabber[0].equalsIgnoreCase("look")) {
       commandInt=3;
    }
    else if (FIGHT_COMMANDS.contains(cmdLine)) {
        commandInt=5;
    }
    else if (cmdLine.equalsIgnoreCase("X")) {
        commandInt=EXIT_COMMAND;
    }
    if(commandInt==0){
        throw new InvalidCommandException(cmdLine+" is not a valid command, please enter in a new one");
    }
    return commandInt;

    }

    /** Method: move(String command, Room room)
     * Will move the player to the next room
     *
     *   @param cmdRoom - the string version of the command
     *
     *   @param room - The current room of the player
     *
     * @result It will updates the players current room if the next room is valid or puts the player back
     * in the current room if not.
     *
     * @return the new room the user is moving to
     *
     *
     * @throws IOException
     */
    private String move(String cmdRoom,Room room) throws IOException{
        String[] commandGrabber=cmdRoom.split(" ",2);

        String moveOutcome="";

        int currentRoomLocation=room.getRoomID() - 1;

        char directionCharacter=commandGrabber[1].charAt(0);
        currentRoomLocation = room.validDirection(directionCharacter);

            if(!(currentRoomLocation == 0)){
                Room nextRoom = new Room(currentRoomLocation-1);

                if (VALID_DIRECTIONS.contains(directionCharacter) && (nextRoom.getRoomID() == currentRoomLocation)){
                    setPrevRoomDirec(""+directionCharacter);
                    Adventure.setRoom(nextRoom);
                    moveOutcome="You moved to the ["+nextRoom.getRoomName()+"] room";
                }
                else{
                    throw new IOException("You cannot move into the new room.");
                }

            }
        return moveOutcome;
    }

    public void setPrevRoomDirec(String direction) throws InvalidRoomException {
        /**Get reverse of direction and set it to String prevRoomDirec*/
        Character cmd=direction.charAt(0);
        List<Character> directions= Arrays.asList('N', 'S', 'E', 'W','U','D');
        if(!directions.contains(cmd)){
            throw new InvalidRoomException("That is not a valid direction");
        }
        if(cmd.equals('N')){
            prevRoomDirec="SOUTH";
        }
        else if(cmd.equals('S')){
            prevRoomDirec="NORTH";
        }
        else if(cmd.equals('U')){
            prevRoomDirec="DOWN";
        }
        else if(cmd.equals('D')){
            prevRoomDirec="UP";
        }
        else if(cmd.equals('E')){
            prevRoomDirec="WEST";
        }
        else if(cmd.equals('W')){
            prevRoomDirec="EAST";
        }



    }








    /** Method: itemCommand(String cmd, Room room)
     * Will execute the item commands when they are called (i.e. get or drop)
     *
     * @Param String cmd - the string version of the command
     * @Param Room room - the current Room from Adventure
     *
     * @result the itemCommand will execute the item cmd and return it to a string
     * @return String - the response to the user's command
     *
     * @throws InvalidCommandException
     * @throws InvalidRoomException
     * @throws InvalidItemException
     */
    public String itemCommand(String cmd, Room room) throws InvalidCommandException,InvalidRoomException,InvalidItemException{

        String[] itemNameGrabber=cmd.split(" ",2);

        ArrayList<String> playerItemsString=new ArrayList<>(),roomItemString=new ArrayList<>();

        for (Item playerItem: player.getInventory()) {playerItemsString.add(playerItem.getName().toUpperCase());}

        for (Item roomItem: room.getRoomItems()) {roomItemString.add(roomItem.getName().toUpperCase());}

        String stringOutput="";
        boolean dropChecker=(itemNameGrabber[0].equalsIgnoreCase("d") || itemNameGrabber[0].equalsIgnoreCase("drop")),
                inspectChecker=(itemNameGrabber[0].equalsIgnoreCase("i") || itemNameGrabber[0].equalsIgnoreCase("inspect")),
                getChecker=(itemNameGrabber[0].equalsIgnoreCase("g") || itemNameGrabber[0].equalsIgnoreCase("get"));

        if (dropChecker) {
                if(!playerItemsString.contains(itemNameGrabber[1])){
                    throw new InvalidCommandException(itemNameGrabber[1]+" does not exist in the players backpack");
                }
                else{
                    stringOutput = drop(itemNameGrabber[1], room);
                }
            }
            else if (getChecker) {
                if(!roomItemString.contains(itemNameGrabber[1])){
                    throw new InvalidRoomException(itemNameGrabber[1]+" does not exist in this room.");}
                else{

                stringOutput = pickUp(itemNameGrabber[1], room);

                }
            }
            else if(inspectChecker){
            try {
                stringOutput = lookItem(cmd, room);
            }catch(IOException e){
                stringOutput=e.toString();
            }

        }

            return stringOutput;
    }


    /** Method: drop(String cmd, Room room)
     * Will drop the current player Item into the current room if it exist
     * @Param String cmd - the string version of the command
     * @Param Room room - The current room
     *
     * @result String  - will return the final string output string; will make sure if the item is dropped
     * into the correct room. If it doesn't exits it will throw an error
     *
     * @throws InvalidRoomException
     * @throws InvalidItemException
     *
     */
    public String drop(String cmd, Room room) throws InvalidRoomException,InvalidItemException{
        if (room == null) {
            throw new InvalidRoomException("There seems to be an error with the room you are in [DROP]");
        }
        Item item=new Item();

        boolean found = false;
        for (Item i : player.getInventory()) {
            if(i.getName().equalsIgnoreCase(cmd)){
                item=i;
                player.removeItem(i);
                room.dropItem(i);
                room.updateRoom();
                found=true;
                break;
            }
        }
        if(!found){
            throw new InvalidItemException(cmd+" was not found in your inventory");
        }

        return item.getName()+" has been dropped into the "+room.getRoomName()+" room.";
    }



    /** Method: pickUP(String cmd, Room room)
     * Will pick up the current room item and
     * @Param String cmd - the string version of the command
     * @Param Room room - The current room
     *
     * @result String  - will return the final string output string ; will make sure if the item is picked
     * up into the correct room, and throws an exception if the room is invalid
     *
     * @throws InvalidRoomException
     * @throws InvalidItemException
     */
    public String pickUp(String cmd, Room room) throws InvalidRoomException,InvalidItemException{

        if (room == null) {
            throw new InvalidRoomException("Pick up room error");
        }
        if (player.getInventory().size()==6){
            throw new InvalidItemException("You cannot fit any more items into your bookbag");
        }

        Item item=new Item();
        boolean found=false;

        for (Item i : room.getRoomItems()) {
            if(i.getName().equalsIgnoreCase(cmd)){
                item=i;
                player.addItem(i);
                room.removeItem(i);
                room.updateRoom();
                found=true;
                break;
            }
        }
        if(!found){
            throw new InvalidItemException(cmd+" was not found in the room");
        }
        return item.getName()+" has been placed in your backpack.";
    }



    public String save(){
        File dbFile = new File("PLAYER_"+player.getName()+".db");
        if (!dbFile.exists()) {
            CreateFilesController cfc = new CreateFilesController();
            try {
                cfc.createFile();
            }
            catch(GameException ge){
                System.out.println(ge.getMessage());
            }
        }



        return "You saved your game";
    }



    public String hint(String cmd,Room room){


        return "Hint text";
    }



    public String help(String cmd, Room r){
        Help txt=new Help();

        return "Help text";
    }



    public String fightCmds(String cmd, Room r){
        String output="Fight Text";
       try {
            if (cmd.equalsIgnoreCase("defend")) {
                output = defend(r);
            } else if (cmd.equalsIgnoreCase("fight")) {
                output = fight(r);
            } else if (cmd.equalsIgnoreCase("run")) {
                output = run(r);
            }
        }catch(InvalidMonsterException | InvalidRoomException e){output=e.getMessage();} catch (IOException e) {
           output=e.getMessage();
       }

        return output;
    }


/**NEED TO DO*/
    public String run(Room x) throws IOException {



        return move("go "+prevRoomDirec,x);
    }



    public String fight(Room x) throws InvalidMonsterException, InvalidRoomException {
        String output="Fighting Text";

        if (x.getMonster() == null) {
            throw new InvalidMonsterException();
        }
        if(x.getMonster().defends()){
            output=x.getMonster().getMonsterName()+" defended itself";
        }
        else{
            if((x.getMonster().getHealth()-player.getStr())<=0){
                x.dropItem(x.getMonster().getDropItem());
                //Need to set the monster as a separate entity and set it to be defeated and Update it
               /** x.updateRoom();*/
            }
            output="Mon HP: "+(x.getMonster().getHealth()-player.getStr());
        }



        return output;
    }




    public String defend(Room x){
        String output="";
        Random random = new Random();
        int ran=random.nextInt(10) +1;
        if(ran>=5){
            output="You sucessfully defended yourself";
        }
        else{
            output="You did were not able to defend yourself\n"+(player.getHp()-x.getMonster().getDamage())+" HP left";

        }
        return output;
    }


    }