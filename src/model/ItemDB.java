package model;

import controller.Item;
import controller.Monster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDB {

    /**
     * Method: getNextItemID
     * Purpose: Gets the id for the next item.
     * @return int
     */
        public int getNextItemID() throws SQLException{
            SQLiteDB sdb = null;
            try {
                sdb = new SQLiteDB();
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            int next = sdb.getMaxValue("ItemID", "Item") + 1;
            //Close the SQLiteDB connection since SQLite only allows one updater
            sdb.close();
            return next;
        }



    /**
     * Method: getItem
     * Purpose: handles db interactions to retrieve a single item
     * @param id
     * @return item
     * @throws SQLException
     */

        public Item getItem(int id) throws SQLException, ClassNotFoundException {
            SQLiteDB sdb = new SQLiteDB();
            Item item = new Item();
            String sql = "Select * from item WHERE itemNumber = " + id;
            ResultSet rs = sdb.queryDB(sql);
            if (rs.next()) {
                item.setId(rs.getInt("itemID"));
                item.setName(rs.getString("itemName"));
                item.setDescription(rs.getString("itemDescription"));
                item.setItemType(rs.getString("itemType"));
                item.setUses(rs.getDouble("Uses"));
                item.setStrBonus(rs.getInt("strBonus"));
                item.setHPBonus(rs.getInt("HPBonus"));
            }
            else {
                throw new SQLException("Item " + id + " not found.");
            }

            //Close the SQLiteDB connection since SQLite only allows one updater
            sdb.close();
            return item;
        }



    /**
     * Method: getAllitems
     * Purpose: Handles the DB interactions to retrieve all items
     * @return ArrayList<item>
     * @throws SQLException
     */
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<Item>();
        SQLiteDB sdb = new SQLiteDB();
        String sql = "Select * from Item";

        ResultSet rs = sdb.queryDB(sql);

        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("itemID"));
            item.setName(rs.getString("itemName"));
            item.setDescription(rs.getString("itemDescription"));
            item.setItemType(rs.getString("itemType"));
            item.setUses(rs.getDouble("Uses"));
            item.setStrBonus(rs.getInt("strBonus"));
            item.setHPBonus(rs.getInt("HPBonus"));
            items.add(item);
        }
        //Close the SQLiteDB connection since SQLite only allows one updater
        sdb.close();
        return items;
    }
}

