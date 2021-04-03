/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticsynthetic;

import methodsforhelp.Utils;
import methodsforhelp.Instructions;
import static choiceofuser.ChoiceUserInputs.creationUserInput;
import static choiceofuser.ChoiceUserInputs.printingLists;
import static choiceofuser.ChoiceUserInputs.userAddingInput;


/**
 *
 * @author Nasos
 */
public class StaticDataBaseOptions {
    
   public  static void ChoiceDataBase(){
               boolean quit = false;
        while (!quit) {
            Instructions.instructionsChoiceOfUserToDo();
            int choice = Utils.checkingIntegers(0,3);
            
                switch (choice) {
                    case 1:
                        creationUserInput();
                        break;
                    case 2:
                        userAddingInput();
                        break;
                    case 3:
                        printingLists();
                        break;
                    case 0:
                        quit = true;
                        break;
                
            }
        }
   
   }
}
