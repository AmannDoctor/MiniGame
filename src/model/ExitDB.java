package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Exit;
import controller.GameController;
import controller.MonsterRoom;
public class ExitDB {
    public ArrayList<Exit> getExits(int roomID) throws SQLException,ClassNotFoundException{
        ArrayList<Exit> exits=new ArrayList<>();
        String sql="";
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            Exit exit=new Exit();
            //mr.setMonsterNumber(rs.getInt("monsterNumber"));
           //mr.setMonsterName(rs.getString("monsterName"));
          //  mr.setMonsterDescription(rs.getString("monsterDescription"));
           exits.add(exit);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return exits;



    }
}