package model;

import controller.*;
import gameExceptions.GameException;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class SavedGameDBCreate {
    SQLiteDB sdb;


    public void saveGame(Player player) throws SQLException, ClassNotFoundException {


    }


    public void loadGame(int score){

    }

    public String buildMonster(Monster mnst){

    return "Hex";
    }
    public String buildPlayer(Player p){

        return "Hex";
    }
    public String buildPuzzle(Puzzle puzzles){

        return "Hex";
    }
    public String buildItems(Item item){
        return "Hex";
    }
    public String buildRooms(Room room){
        return "Hex";
    }
    public String hexbuilder(String str){
        StringBuffer sb = new StringBuffer();
        //Converting string to character array
        char ch[] = str.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        String result = sb.toString();
        return result;
    }
    public String hexDecrepter(String str){
        String result = new String();
        char[] charArray = str.toCharArray();
        for(int i = 0; i < charArray.length; i=i+2) {
            String st = ""+charArray[i]+""+charArray[i+1];
            char ch = (char)Integer.parseInt(st, 16);
            result = result + ch;
        }
        return result;
    }




}