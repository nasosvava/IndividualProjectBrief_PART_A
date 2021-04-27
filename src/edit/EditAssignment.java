/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import java.util.Scanner;
import mainclasses.Assignments;
import mainclasses.Course;
import methodsforhelp.Instructions;
import methodsforhelp.Utils;
import static methodsforhelp.Utils.checkingIntegers;
import static methodsforhelp.Utils.checkingStringToBeOnlyLetters;
import methodsforhelp.colour;
import selection.SelectAssignments;
import selection.SelectCourses;

/**
 *
 * @author Nasos
 */
public class EditAssignment {

    private static Scanner scanner = new Scanner(System.in);

    private static void editAssignment() {

        System.out.println(colour.TEXT_GREEN + "Please Select Assignmet for edit." + colour.TEXT_RESET);
        Assignments a = SelectAssignments.selectOneAssignment(Assignments.getAssignmentsList());

        boolean quit = false;
        while (!quit) {
            System.out.println(a);
            System.out.println(colour.TEXT_GREEN + "What you want to update? Press:" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_PURPLE + "0)To go back" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "1)Title" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "2)Description" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "3)Oral Mark" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "4)Total Mark" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "5)Sub Date Time" + colour.TEXT_RESET);
            System.out.println("");
            int choice = Utils.checkingIntegers(0, 6);

            switch (choice) {
                case 1:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(a.getTitle());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give Assignmet Title : " + colour.TEXT_RESET);
                    a.setTitle(scanner.nextLine());
                    break;
                case 2:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(a.getDescription());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give Assignmet Description : " + colour.TEXT_RESET);
                    a.setDescription(scanner.nextLine());
                    break;
                case 3:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(a.getOralMark());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give Assignment Oral Mark : " + colour.TEXT_RESET);
                    a.setOralMark(checkingIntegers());
                case 4:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(a.getTotalMark());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give Assignment Total Mark : " + colour.TEXT_RESET);
                    a.setTotalMark(checkingIntegers());
                    break;
                case 5:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(a.getSubDateTime());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give Assignment Sub Date Time : " + colour.TEXT_RESET);
                    System.out.println(colour.TEXT_YELLOW + "dd-MM-yyyy" + colour.TEXT_RESET);
                    a.setSubDateTime(Utils.validationLocalDate());
                case 0:
                    quit = true;
                    break;
            }

            a.updateAssignment();

        }
    }

    public static void menuEditAssignment() {
        while (true) {
            editAssignment();
            Instructions.IntsructionsSelectionBetweenOneOrTwo();
            boolean result = Utils.optionsTrueOrFalse();
            if (result) {
                System.out.println(colour.TEXT_GREEN + "Lets edit a assignment!" + colour.TEXT_RESET);
            } else {
                break;
            }
        }

    }
}
