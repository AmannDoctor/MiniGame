package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.GameController;
import controller.MonsterRoom;

/**
 * Class : MonsterRoomDB.java
 * @author: Rick Price
 * @version: 1.0
 * Course: ITEC 3860
 * Written: March 29, 2021
 * Purpose: Manage database interactions for the MonsterRoom association table
 */
public class MonsterRoomDB {
    /**
     * Method: getMonster
     * Purpose: get the monster from a specific room
     * @param roomID
     * @return MonsterRoom
     * @throws SQLException
     */
    public MonsterRoom getMonster(int roomID) throws SQLException, ClassNotFoundException {
        MonsterRoom mr = new MonsterRoom();
        String sql = "SELECT DISTINCT m.monsterNumber, m.monsterName, m.monsterDescription, m.hitPoints, "
                + " m.maxDamage, m.minDamage, r.roomNumber, r.roomName, r.roomDescription FROM Monster m, room r" +
                " INNER JOIN MonsterRoom mr on mr.monsterNumber = m.monsterNumber where r.roomNumber = " + roomID + " ORDER BY mr.monsterNumber";
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {

            /**Need to add in damage)*/



            mr.setMonsterNumber(rs.getInt("monsterNumber"));
            mr.setMonsterName(rs.getString("monsterName"));
            mr.setMonsterDescription(rs.getString("monsterDescription"));
            mr.setHitPoints(rs.getInt("hitPoints"));

            mr.setRoomNumber(rs.getInt("roomNumber"));
            mr.setRoomName(rs.getString("roomName"));
            mr.setRoomDescription(rs.getString("roomDescription"));
        }
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return mr;
    }

    /**
     * Method: getAllMonsters
     * Purpose: get the list of all monsters and their rooms
     * @return ArrayList<Monster>
     * @throws SQLException
     */
    public ArrayList<MonsterRoom> getAllMonsters() throws SQLException, ClassNotFoundException {
        ArrayList<MonsterRoom> monsterRooms = new ArrayList<MonsterRoom>();
        String sql = "SELECT DISTINCT Monster.monsterNumber, Monster.monsterName, Monster.monsterDescription, Monster.hitPoints, "
                + " Monster.maxDamage, Monster.minDamage, Room.roomNumber, Room.roomName, Room.roomDescription FROM MonsterRoom " +
                "INNER JOIN Room ON MonsterRoom.roomNumber = Room.roomNumber " +
                " INNER JOIN Monster on MonsterRoom.monsterNumber = Monster.monsterNumber";
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            MonsterRoom mr = new MonsterRoom();
            mr.setMonsterNumber(rs.getInt("monsterID"));
            mr.setMonsterName(rs.getString("monsterName"));
            mr.setMonsterDescription(rs.getString("monsterDescription"));
            mr.setHitPoints(rs.getInt("hitPoints"));
            mr.setMaxDamage(rs.getInt("maxDamage"));
            mr.setMinDamage(rs.getInt("minDamage"));
            mr.setRoomNumber(rs.getInt("roomNumber"));
            mr.setRoomName(rs.getString("roomName"));
            mr.setRoomDescription(rs.getString("roomDescription"));
            monsterRooms.add(mr);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return monsterRooms;
    }
}