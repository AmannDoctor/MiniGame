import java.io.File;
import java.util.IllegalFormatException;
import java.util.Scanner;

import controller.CreateFilesController;
import gameExceptions.*;
import view.ConsoleUI;


/**
 * Class : GameStart.java
 * @author: Rick Price
 * @version: 1.0
 * Course: ITEC 3860
 * Written: March 29, 2021
 * This class is the launch point for the DB demo
 */
public class GameStart {

    public static void main(String[] args) {
        File dbFile = new File("ScientistDBFinalized");
        if (!dbFile.exists()) {
            CreateFilesController cfc = new CreateFilesController();
            try {
                cfc.createFile();
            }
            catch(GameException ge){
                System.out.println(ge.getMessage());
            }
        }

        Scanner input = new Scanner(System.in);
        ConsoleUI cui = new ConsoleUI();
        cui.startGame();




        input.close();
        System.exit(0);
    }
}
