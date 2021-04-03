/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecta;

import choiceofuser.ChoiceUserInputs;
import staticsynthetic.StaticDataBaseOptions;
import staticsynthetic.SyntheticData;
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
    public static void creationMethodOrSynthetic() {
        boolean quit = false;
        boolean stop = true;
        while (!quit) {
            InstructionsForSynthetic();
            String input = scanner.next().toLowerCase();
            input = input.trim();
            switch (input) {
               case "1":
                    if (stop) {  //This is if runs only one time so if the user wants to run the programm from the very begining to dont take second time the synthetic
                        SyntheticData.Data();  
                    }
                    stop = false;
                    StaticDataBaseOptions.ChoiceDataBase();
                    break;
                case "2":
                    ChoiceUserInputs.choiceOfUserToDo();
                    break;
                case "exit":
                    System.out.println(colour.TEXT_PURPLE + "Thanks for your time!" + colour.TEXT_RESET);
                    quit = true;
                    break;
                default:
                    System.out.println(colour.TEXT_RED + "You typed something wrong!" + colour.TEXT_RESET);
                    break;
            }

        }
    }

}
