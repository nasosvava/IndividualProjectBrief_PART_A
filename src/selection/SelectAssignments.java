/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selection;

import mainclasses.Assignments;
import java.util.ArrayList;
import java.util.Scanner;
import methodsforhelp.Instructions;
import methodsforhelp.Utils;
import methodsforhelp.colour;

/**
 *
 * @author Nasos
 */
public class SelectAssignments {

    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Select many Assignments for Assignment List.
     * @param assignmentList
     * @return ArrayList with selected Assignments
     */
    public static ArrayList<Assignments> selectManyAssignments(ArrayList<Assignments> assignmentList) {
        ArrayList<Assignments> selectedAssignments = new ArrayList();
        boolean quit = false;

        while (quit == false) {
            if (assignmentList.isEmpty()) {
                System.out.println(colour.TEXT_RED+"You dont have more assingments.Make your next Choice!"+colour.TEXT_RESET);
                quit = true;
            } else {
                int counter = 1;

                System.out.println(colour.TEXT_YELLOW+"Select an Assignment"+colour.TEXT_RESET);

                for (Assignments assignment : assignmentList) {
                    System.out.println(counter + " " + assignment.getTitle() + " " + assignment.getDescription());
                    counter++;
                }
                int choice = Utils.checkingIntegers(1, assignmentList.size());

                selectedAssignments.add(assignmentList.get(choice - 1));
                assignmentList.remove(choice - 1);

                Instructions.IntsructionsSelectionBetweenOneOrTwo();

                boolean input = Utils.optionsTrueOrFalse();

                if (input == true) {
                    System.out.println(colour.TEXT_YELLOW+"Select the next one!"+colour.TEXT_RESET);
                } else {
                    System.out.println(colour.TEXT_RED+"You finished"+colour.TEXT_RESET);
                    quit = true;
                }
            }
        }
        return selectedAssignments;

    }
    
    /**
     * Select one Assignments for Assignment List.
     * @param assignmentList
     * @return Assignment
     */
    public static Assignments selectOneAssignment(ArrayList<Assignments> assignmentList) {
        System.out.println(colour.TEXT_YELLOW+"Choose one assignment!"+colour.TEXT_RESET);
        int counter = 1;
        for (Assignments assignment : assignmentList) {
            System.out.println(counter + " " + assignment.getTitle() + " " + assignment.getDescription());
            counter++;
        }
        int i = Utils.checkingIntegers(1, assignmentList.size());
        Assignments assignment = assignmentList.get(i - 1);
        return assignment;
    }
}
