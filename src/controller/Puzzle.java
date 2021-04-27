package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Puzzle {

    private int ID;
    private int points;
    private boolean completed;
    private ArrayList<Item> items =new ArrayList<>();
    private static ArrayList<Boolean> checkmarks;
    private boolean mandatory;
    private Scanner input;


    public Puzzle(){
        checkmarks=new ArrayList<>();
        input=new Scanner(System.in);

    }
    public void solvePuzzle(){

    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String PuzzleOne(String cmd){
        //Item id 1
        String outcome="";
        checkmarks=new ArrayList<>(2);
        String[] cmdGrabber=cmd.split(" ",2);
        if(cmdGrabber[0].equalsIgnoreCase("Grab")&&cmdGrabber[1].equalsIgnoreCase("Key")){

            checkmarks.add(0,true);
            outcome="Grabbed Key";


        }
        if(checkmarks.get(0)&&(cmdGrabber[0].equalsIgnoreCase("Use")&&cmdGrabber[1].equalsIgnoreCase("Key"))){
            checkmarks.add(1,true);
            outcome="Used Key";
        }

        return outcome;

    }
    public String PuzzleTwo(String cmd){
        checkmarks=new ArrayList<>(1);
        String[] cmdGrabber=cmd.split(" ",2);
        String outcome="";
        if(cmdGrabber[0].equalsIgnoreCase("Use")&&cmdGrabber[1].equalsIgnoreCase("Window")){
            checkmarks.add(0,true);
            outcome="Opened Window";
        }


        return outcome;
    }
    public String PuzzleThree(String cmd){
        String outcome ="";
        checkmarks=new ArrayList<>(2);
        String[] cmdGrabber=cmd.split(" ",2);


        if(cmdGrabber[0].equalsIgnoreCase("Use")&&cmdGrabber[1].equalsIgnoreCase("Head")){

            checkmarks.add(0,true);
            System.out.println("The head’s eyes begin to glow a bright blue color. “Speaketh thy password!” it yells. \n Enter the password:");
            String password=input.nextLine().toLowerCase();

            if(password.equals("weird science")){
                checkmarks.add(1,true);
                outcome="Password correct! The room begins to rumble, and a loud, machine-like sound echoes from the hallway.";
            }
            else{
               outcome="Wrong password! The glow in the head’s eyes fades away";
            }

        }




        return outcome;
    }


    public String PuzzleFour(Room r,Player p){
        //Item IDs 8 through 13 and item id 15; Order Science Book, Holy Book, Music Book, Cookbook, History Book, Art Book
        HashMap<Item, Boolean> orderList;



        return "outcome";
    }

    public String PuzzleFive(Room r){
        //Item IDs 16 through 18; (From left to right: bright, drab, dark)


        HashMap<Item, Boolean> orderList;
        return "outcome";
    }


    public String PuzzleSix(String cmd){
        //Item ID 15
        String outcome="";
        checkmarks=new ArrayList<>(1);
        String[] cmdGrabber=cmd.split(" ",2);
        if(cmdGrabber[0].equalsIgnoreCase("Use")&&cmdGrabber[1].equalsIgnoreCase("Cassette Tape")){

            checkmarks.add(0,true);
            outcome="Password correct! The room begins to rumble, and a loud, machine-like sound echoes from the hallway.";



        }

        return outcome;

    }

    public String PuzzleSeven(String cmd){
        String outcome="";
        checkmarks=new ArrayList<>(1);
        String[] cmdGrabber=cmd.split(" ",2);
        if(cmdGrabber[0].equalsIgnoreCase("Use")&&cmdGrabber[1].equalsIgnoreCase("Chain")){
            checkmarks.add(0,true);
            outcome="Used the Chain";
        }

        return outcome;

    }
    public String PuzzleEight(String cmd){
        String outcome="";
        checkmarks=new ArrayList<>(1);
        String[] cmdGrabber=cmd.split(" ",2);
        if(cmdGrabber[0].equalsIgnoreCase("Use")&&cmdGrabber[1].equalsIgnoreCase("Water")){

            checkmarks.add(0,true);
            outcome="Splashed the water";

        }

        return outcome;

    }

    public String PuzzleNine(String cmd){
        String outcome="";
        checkmarks=new ArrayList<>(2);
        String[] cmdGrabber=cmd.split(" ",2);
        if(cmdGrabber[0].equalsIgnoreCase("Examine")&&cmdGrabber[1].equalsIgnoreCase("Note")){

            checkmarks.add(0,true);
            System.out.println("The more of this there is, the less you see. What is it?");
            String password=input.nextLine().toLowerCase();

            if(password.equals("weird science")){
                checkmarks.add(1,true);
                outcome="Password correct! The room begins to rumble, and a loud, machine-like sound echoes from the hallway.";
            }
            else{
                outcome="Wrong password! The glow in the head’s eyes fades away";
            }

        }

        return outcome;

    }
    public String PuzzleTen(String cmd){
        String outcome="";
        checkmarks=new ArrayList<>(2);
        String[] cmdGrabber=cmd.split(" ",2);
        if(cmdGrabber[0].equalsIgnoreCase("Use")&&cmdGrabber[1].equalsIgnoreCase("Chair")){

            checkmarks.add(0,true);
            outcome="Moved Chair";


        }
        if(checkmarks.get(0)&&(cmdGrabber[0].equalsIgnoreCase("Grab")&&cmdGrabber[1].equalsIgnoreCase("Strenth potion"))){
            checkmarks.add(1,true);
            outcome="Grabbed Potion";
        }

        return outcome;

    }


    public int getPoints() {
        return points;
    }

    public boolean isCompleted() {
        return completed;
    }
    public boolean puzzleCompletions()
    {
        for(boolean b : checkmarks) if(!b) return false;
        return true;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setCheckmarks(ArrayList<Boolean> checkmarks) {
        this.checkmarks = checkmarks;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}