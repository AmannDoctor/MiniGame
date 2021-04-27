package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Exit;
import controller.GameController;
import controller.Item;
import controller.MonsterRoom;
public class ExitRoomDB {

    public int getNextExit() throws SQLException {
        SQLiteDB sdb = null;
        try {
            sdb = new SQLiteDB();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int next = sdb.getMaxValue("RoomID", "Exits") + 1;
        //Close the SQLiteDB connection since SQLite only allows one updater


        sdb.close();

        return next;
    }


    public ArrayList<Exit> getExits(int roomID) throws SQLException,ClassNotFoundException{
        ArrayList<Exit> exits=new ArrayList<>();
        String sql="Select * from Exits WHERE North > 0 OR South > 0 OR East > 0 OR West > 0 OR Up > 0 OR Down > 0";
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);

        while (rs.next()) {
            Exit exit = new Exit();

            exit.setRoomNum(rs.getInt("RoomID"));
            exit.setDirection(rs.getString("Direction"));
            exits.add(exit);

        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return exits;
    }
    public String getRoomExitString(int RoomID) throws SQLException, ClassNotFoundException {
        String out=new String();


        String sql="Select * from Exits WHERE roomID = "+RoomID;
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);

        while (rs.next()) {
           Exit exit=new Exit();

            exit.setRoomNum(rs.getInt("RoomNum"));
            exit.setDirection(rs.getString("Direction"));

            System.out.println(exit.toString());
        }

        return "";
    }

}