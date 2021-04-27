package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ItemRoom;

/**
 * Class : MonsterRoomDB.java
 * @author: Rick Price
 * @version: 1.0
 * Course: ITEC 3860
 * Written: March 29, 2021
 * Purpose: Manage database interactions for the ItemRoom association table
 */
public class ItemRoomDB {
    /**
     * Method: getItem
     * Purpose: get the item from a specific room
     * @param roomID
     * @return ItemRoom
     * @throws SQLException
     */
    public ItemRoom getItem(int roomID) throws SQLException, ClassNotFoundException {
        ItemRoom ir = new ItemRoom();
        String sql = "SELECT DISTINCT i.itemNumber, i.itemName, i.itemDescription, i.itemType, i.Uses, "
                + " i.strBonus, i.HPBonus, r.roomNumber, r.roomName, r.roomDescription FROM Item i, room r" +
                " INNER JOIN ItemRoom ir on ir.itemNumber = i.itemNumber where r.roomNumber = " + roomID + " ORDER BY ir.itemNumber";
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
        	ir.setItemNumber(rs.getInt("itemNumber"));
            ir.setItemName(rs.getString("itemName"));
            ir.setItemDescription(rs.getString("itemDescription"));
            ir.setItemType(rs.getString("itemType"));
            ir.setUses(rs.getDouble("Uses"));
            ir.setStrBonus(rs.getInt("strBonus"));
            ir.setHPBonus(rs.getInt("HPBonus"));
            ir.setRoomNumber(rs.getInt("roomNumber"));
            ir.setRoomName(rs.getString("roomName"));
            ir.setRoomDescription(rs.getString("roomDescription"));
        }
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return ir;
    }

    /**
     * Method: getAllItems
     * Purpose: get the list of all items and their rooms
     * @return ArrayList<Item>
     * @throws SQLException
     */
    public ArrayList<ItemRoom> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemRoom> itemRooms = new ArrayList<ItemRoom>();
        String sql = "SELECT DISTINCT Item.itemNumber, Item.itemName, Item.Mandatory, Item.itemType, "
                + " Item.Uses, Item.strBonus, Item.HPBonus, Room.roomNumber, Room.roomName, Room.roomDescription FROM ItemRoom " +
                "INNER JOIN Room ON ItemRoom.roomNumber = Room.roomNumber " +
                " INNER JOIN Item on ItemRoom.itemNumber = Item.itemNumber";
        SQLiteDB sdb = new SQLiteDB();
        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            ItemRoom ir = new ItemRoom();
            ir.setItemNumber(rs.getInt("itemNumber"));
            ir.setItemName(rs.getString("itemName"));
            ir.setItemDescription(rs.getString("itemDescription"));
            ir.setItemType(rs.getString("itemType"));
            ir.setUses(rs.getDouble("Uses"));
            ir.setStrBonus(rs.getInt("strBonus"));
            ir.setHPBonus(rs.getInt("HPBonus"));
            ir.setRoomNumber(rs.getInt("roomNumber"));
            ir.setRoomName(rs.getString("roomName"));
            ir.setRoomDescription(rs.getString("roomDescription"));
            itemRooms.add(ir);
        }

        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return itemRooms;
    }
}