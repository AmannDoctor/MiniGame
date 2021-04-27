package controller;



import java.util.*;

/**Class: Player
 * @author Mackinnon Jackson
 * @version 2.0
 *  Course: ITEC 3860 Spring 2021
 *  Written: 3/19/2021
 *
 *  This class –
 *  The Player class will keep track of and stores the users backpack as they go from room to room
 *  */


public class Player {
    /**Backpack -- The player’s backpack should be able to hold six items.
     * This includes any weapons or armor the player has currently equipped.
     * If the player’s backpack is full, they will need to Drop or Use an item to make room to be able to grab another item
     */
    private ArrayList<Item> inventory;
    private String name;
    private int hp;
    private Item equippedItem;
    private Item equippedArmor;
    private int str, score,armor;
    private Boolean isEquipedWeapon = false;
    private Boolean isEquipedArmor = false;



    /**Method Player
     * -------------
     * Constructor for the Player class
     * Instantiates the ArrayList to hold the Inventory*/
    public Player() {
        hp=100;
        str=10;
        inventory = new ArrayList<>();

    }



    /**Method Player
     * -------------
     * @param Name - String for the player name
     * Constructor for the Player class
     * Instantiates the ArrayList to hold the Inventory*/
    public Player(String Name) {
        this.name=Name;
        hp=100;
        str=10;
        inventory = new ArrayList<>();}

    /** Method: PrintInventory
 * Prints out the users inventory in string
     * @result the string version of the users inventory of there is any, or will return "You currently do not have
     * anything in your backpack if there is nothing.
     * @return String backpackList
    */
    public String printInventory() {
        String backpackList = "";
        if(!inventory.isEmpty()){
            backpackList="There are currently "+ inventory.size()+" item(s) in your backpack:\n";
        for (Item item : inventory) {
            backpackList = backpackList + item.getName()+" ";
        }

        }
        else{
            backpackList="You currently do not have anything in your backpack.";
        }
        return backpackList;
    }


    public void addItem(Item it){
        inventory.add(it);

    }
    public void removeItem(Item it){
        inventory.remove(it);}

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

    public int getStr() {
        return str;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public ArrayList<Item> getInventory() {return inventory;}
    public int getScore(){return score;}

    public void updateScore(int score) {
        this.score = this.score+score;
    }

    public String getName() {
        return name;
    }
    public void equipItem(Item it){


        if( it.getHPBonus() == 0 && isEquipedWeapon == false) {
            equippedItem = it;
            it.getStrBonus();
            isEquipedWeapon = true;

            System.out.println(it.getStrBonus());
            str = str + it.getStrBonus();
            System.out.println("your new Strength is " + str + ".");
        }
        //armor check
        else if(it.getHPBonus() != 0 && isEquipedArmor == false){
            equippedArmor = it;
            isEquipedArmor= true;
            it.getHPBonus();

            System.out.println(it.getHPBonus());
            armor = armor + it.getHPBonus();
            System.out.println("Your new armor is " + armor + ".");
        }
        else
            System.out.println("You Seemed to already have " + it + " equipped.");
    }

    public void unEquip(Item it){

        if( equippedItem != null && equippedItem == it && isEquipedWeapon == true) {
            it.getStrBonus();

            isEquipedWeapon = false;
            System.out.println(it.getStrBonus());
            str = str - it.getStrBonus();
            System.out.println("your new Strength is " + str + ".");
        }
        //armor check
        else if(equippedArmor != null && equippedArmor== it && isEquipedArmor == true){
            it.getHPBonus();

            isEquipedArmor = false;
            System.out.println(it.getHPBonus());
            armor = armor - it.getHPBonus();
            System.out.println("Your new armor is " + armor + ".");
        }


    }

}








