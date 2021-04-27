package controller;

import model.MonsterDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Monster {
    private int monsterID;
    private boolean defeated;
    private String monsterName;
    private int health,damage,points,tempHP;
   private Item dropItem;
    private String description;
    private String fightMessage;
    private String defendMessage;
    private boolean mandatory;
    public Monster() {
        MonsterDB mdb = new MonsterDB();
        try {
            monsterID = mdb.getNextMonsterID();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
    }

    public Monster(int monsterID, boolean defeated, String monsterName, int health, int damage, int points, Item dropItem, String description, String fightMessage, String defendMessage, boolean mandatory) {
        this.monsterID = monsterID;
        this.defeated = defeated;
        this.monsterName = monsterName;
        this.health = health;
        tempHP=health;
        this.damage = damage;
        this.points = points;
        this.dropItem = dropItem;
        this.description = description;
        this.fightMessage = fightMessage;
        this.defendMessage = defendMessage;
        this.mandatory=mandatory;
    }
    public Monster getMonster(int id) throws SQLException, ClassNotFoundException {
        MonsterDB mdb = new MonsterDB();
        return mdb.getMonster(id);
    }

    /**
     * Method: getAllMonsters
     * Purpose: gets all monsters from the Monster table
     * @return ArrayList<Monster>
     * @throws SQLException
     */
    public ArrayList<Monster> getAllMonsters() throws SQLException, ClassNotFoundException {
        MonsterDB mdb = new MonsterDB();
        return mdb.getAllMonsters();
    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Item getDropItem() {
        return dropItem;
    }

    public void setDropItem(Item dropItem) {
        this.dropItem = dropItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFightMessage() {
        return fightMessage;
    }

    public void setFightMessage(String fightMessage) {
        this.fightMessage = fightMessage;
    }

    public String getDefendMessage() {
        return defendMessage;
    }

    public void setDefendMessage(String defendMessage) {
        this.defendMessage = defendMessage;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Boolean defends(){
        Random random = new Random();
        int dm=3;
        if (tempHP<=health){
            dm=5;
        }
        int ran=random.nextInt(10) +1;
        if(ran<dm){
         return true;
        }
        return false;
    }


}