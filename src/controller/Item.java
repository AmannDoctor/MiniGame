package controller;

import gameExceptions.InvalidItemException;
import model.ItemDB;
import model.MonsterDB;

import java.sql.SQLException;
import java.util.ArrayList;

/** Class: Item
 *Creator: Mackinnon Jackson
 *version 2.0
 *Course: ITEC 3860 Spring 2021
 *Written: 3/19/2021
 *
 * This class -
 * Will store the information about each item
 */
public class Item {

    private int id;
    private String name, description;
    private boolean equipable;
    private double uses;
    private String itemType;
    private int strBonus;
    private int HPBonus;

    /**Method Item
     * Constructor for the Item class
     * ----------------------------------------
     *  Creates an blank item
     * */
    public Item(){
    ItemDB imd = new ItemDB();
        try {
            id = imd.getNextItemID();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }

    }
    /**Method Item
     * Constructor for the Item class
     * ----------------------------------------
     *  Creates an item with an ID, name, and description
     * @param ID - Item ID as an Int
     * @param name - The item name as a string
     * @param description - The item description as a string
     * */
    public Item(int ID,String name, String description){
        this.id=ID;
        this.name=name;
        this.description=description;
    }


    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ItemDB idb = new ItemDB();
        return idb.getAllItems();
    }

    public String getDescription(){
        return description;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }


    public String display() throws InvalidItemException {
        if (getDescription().equals(null)||getName().equals(null)) {

            throw new InvalidItemException("There is an error with displaying an item");
        }
        return getName()+" description: "+getDescription();

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUses() {
        return uses;
    }
    public void setUses(double uses){
        this.uses=uses;
    }

    public String getItemType() {
        return itemType;
    }

    public boolean isEquipable() {
        return equipable;
    }

    public void setEquipable(boolean equipable) {
        this.equipable = equipable;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrBonus(int strBonus) {
        this.strBonus = strBonus;
    }

    public int getStrBonus() {
        return strBonus;
    }

    public void setHPBonus(int HPBonus) {
        this.HPBonus = HPBonus;
    }

    public int getHPBonus() {
        return HPBonus;
    }

    @Override
    public String toString() {
        return "(Item ID: " +
                 id +"), (Item Name: " +
                name + "), (Item description:" + description +")";
    }
}