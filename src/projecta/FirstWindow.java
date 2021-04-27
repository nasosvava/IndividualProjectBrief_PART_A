/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecta;

import choiceofuser.ChoiceUserInputs;
import java.sql.SQLException;
import java.util.Scanner;
import static methodsforhelp.Instructions.InstructionsForSynthetic;
import methodsforhelp.colour;

/**
 *
 * @author Nasos
 */
public class FirstWindow {

    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Methods to choose if you want synthetic or not!
     */
    public static void creationMethodOrSynthetic() throws SQLException {
        boolean quit = false;
        boolean stop = true;
        while (!quit) {
            InstructionsForSynthetic();
            String input = scanner.next().toLowerCase();
            input = input.trim();
            switch (input) {
                case "exit":
                    System.out.println(colour.TEXT_PURPLE + "Thanks for your time!" + colour.TEXT_RESET);
                    quit = true;
                    break;
                default:
                    ChoiceUserInputs.choiceOfUserToDo();
                    break;
            }

        }
    }

}
