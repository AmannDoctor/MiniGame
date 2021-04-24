package model;


import controller.*;
import gameExceptions.*;
import java.io.*;
import java.util.*;

    /**Class: MapModel
     * @author Mackinnon Jackson
     * @version 2.0
     *  Course: ITEC 3860 Spring 2021
     *  Written: 3/21/2021
     *
     *  This class –
     *  This class will generate the map and room items for the game based off the information found in the
     *  Rooms.txt and Item.txt files.
     *  */
    public class MapModel {
        private ArrayList<Room> map;

        private ArrayList<Item> roomItems;

        /**Constructor: Map()
         * Instatiates the ArrayList to hold items and rooms
         *
         *  @result A map object that is filled with different rooms and items*/
        public MapModel() {
            map= new ArrayList<>();
            roomItems = new ArrayList<>();
        }



        private Item getItem(int id) throws InvalidRoomException{
            Item outputItem=new Item();
            for (Item i:roomItems) {
                if(i.getId()==id){
                    outputItem=i;
                }
            }

            return outputItem;
        }



        public Room getRoom(int roomNumber) throws InvalidRoomException {
            if (map.get(roomNumber) == null) {
                throw new InvalidRoomException("Room "+roomNumber+" does not exist");
            }
            return map.get(roomNumber);
        }

        public ArrayList<Item> getItems(int roomID) throws InvalidRoomException{
            if (map.get(roomID) == null) {
                throw new InvalidRoomException("Room "+roomID+" does not exist");
            }
            return map.get(roomID).getRoomItems();
        }


        public void readRoom() throws IOException,IndexOutOfBoundsException,FileNotFoundException{
            try {
                Scanner scanner = new Scanner(new File("Rooms.txt"));
                while (scanner.hasNextLine()) {
                    addRoom(scanner.nextLine());
                }
                scanner.close();
            }

            catch (IndexOutOfBoundsException e){throw new IndexOutOfBoundsException("I am sorry, but there is an Out of Bounds error with the Rooms area.");}
            catch (IOException e){throw new IOException("I am sorry, but the controller.Room file that was suppose to be attached to the document cannot be found. Please try and get a new version of the game.");}
        }



        public void readItems() throws IOException {
            try {
                Scanner scanner = new Scanner(new File("Item.txt"));
                while (scanner.hasNextLine()) {
                    addItem(scanner.nextLine());
                }
                scanner.close();

            }
            catch (IndexOutOfBoundsException e){throw new IndexOutOfBoundsException("I am sorry, but there is an Out of Bounds error with the controller.Item area.");}
            catch (IOException e){throw new IOException("I am sorry, but the controller.Item file that was suppose to be attached to the document cannot be found. Please try and get a new version of the game.");}
        }


        /**Method: getMap()
         * Gathers information about the map and returns the details
         *
         *  @result Will convert the map information into a string
         * @return String */
        public String getMap() {
            String details = "";
            for (Room room : map) {
                details = details +room.toString();
                try{
                if (!room.getRoomItems().isEmpty()){
                    for (Item i:getItems(room.getRoomID()-1)) {
                        details = details+i.display()+"\n";
                    }


                }} catch(IOException e){

                }
                details = details +"\n";
            }
            for (Item item:roomItems) {
                details = details +item.toString() +"\n";
            }

            return details;
        }


        /**Method: addRoom(String line)
         * Will take in each line from the scanner that is reading the Room.txt and makes it into an Room object
         *  @param line String line from the Rooms.txt file and converts it over to a room object
         *
         *  @result Will add a new Room object into the ArrayList map
         *
         *  @throws InvalidRoomException*/
        public void addRoom(String line) throws InvalidRoomException{
            try {
                Room room=new Room();
                String[] details = line.split(" ; ");
                room.setRoomID(Integer.valueOf(details[0]));
                room.setRoomName(details[1]);
                String[] pairs = details[3].split(",");
                ArrayList<Exit> exitArrayList=new ArrayList<>();
                for (String pair: pairs) {Exit exit=new Exit();
                    try {exit.buildExit(pair);}
                    catch(InvalidExitException e){e.printStackTrace();}
                  exitArrayList.add(exit);}
                room.setDescription(details[2]);
                room.setVisited(false);
                room.setExits(exitArrayList);
                if(details.length==5){
                    ArrayList<Item> itemsArrayList=new ArrayList<>();
                    String[] itemList=details[4].split(",");
                    for (String item:itemList) {
                        String[] itemsPair=item.split(":");
                       itemsArrayList.add(getItem(Integer.valueOf(itemsPair[1])));

                        room.setRoomItems(itemsArrayList);
                    }
                }
                map.add(room);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        /**Method: addItem(String line)
         * * Will take in each line from the scanner that is reading the Room.txt and makes it into an Item object
         *
         *  @param line String line read from the item.txt file
         *
         *  @result Will add the new item objects into the Item ArrayList */
        public void addItem(String line) {
            try {
                String[] details = line.split(" ; ");
                int itemNum=Integer.valueOf(details[0]);
                roomItems.add(new Item(itemNum,details[1],details[2]));
            }catch (NumberFormatException e) {e.printStackTrace();}
        }



        /** Method: updateRoom(Room rm)
         * Will take in a room and updates it in the map
         *
         * @param rm - current Room object being updated
         *
         * @result Updates the room in the current map throws an exception if the room is not found
         *
         * @throws InvalidRoomException
         *
         */
        public void updateRoom(Room rm) throws InvalidRoomException{
            int roomID= rm.getRoomID()-1;
            map.set(roomID,rm);
        }





}