package controller;

import model.ItemRoomDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemRoom {
    private int itemNumber;
    private String itemName;
    private String itemDescription;
    private String itemType;
    private double Uses;
    private int strBonus;
    private int HPBonus;
    private int roomNumber;
    private String roomName;
    private String roomDescription;

    public ItemRoom getItem(int roomID) throws SQLException, ClassNotFoundException {
        ItemRoomDB mdb = new ItemRoomDB();
        return mdb.getItem(roomID);
    }

    /**
     * Method: getAllItems
     * Purpose: gets all items and their associated rooms
     * @return ArrayList<ItemRoom>
     * @throws SQLException
     */
    public ArrayList<ItemRoom> getAllItems() throws SQLException, ClassNotFoundException {
        ItemRoomDB mdb = new ItemRoomDB();
        return mdb.getAllItems();
    }

    /**
     * @return the itemNumber
     */
    public int getItemNumber() {
        return itemNumber;
    }

    /**
     * @param itemNumber the itemNumber to set
     */
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the uses
     */
    public double getUses() {
        return Uses;
    }

    /**
     * @param uses the uses to set
     */
    public void setUses(double uses) {
        Uses = uses;
    }

    /**
     * @return the strBonus
     */
    public int getStrBonus() {
        return strBonus;
    }

    /**
     * @param strBonus the strBonus to set
     */
    public void setStrBonus(int strBonus) {
        this.strBonus = strBonus;
    }

    /**
     * @return the hPBonus
     */
    public int getHPBonus() {
        return HPBonus;
    }

    /**
     * @param hPBonus the hPBonus to set
     */
    public void setHPBonus(int hPBonus) {
        HPBonus = hPBonus;
    }

    /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the roomDescription
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * @param roomDescription the roomDescription to set
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    @Override
    public String toString() {
        return "ItemRoom [itemNumber=" + itemNumber + ", itemName=" + itemName + ", itemDescription=" + itemDescription
                + ", itemType=" + itemType + ", Uses=" + Uses + ", strBonus=" + strBonus + ", HPBonus=" + HPBonus
                + ", roomNumber=" + roomNumber + ", roomName=" + roomName + ", roomDescription=" + roomDescription
                + "]";
    }
}