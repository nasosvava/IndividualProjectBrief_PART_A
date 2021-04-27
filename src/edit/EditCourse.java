/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import java.util.Scanner;
import mainclasses.Course;
import methodsforhelp.Instructions;
import methodsforhelp.Utils;
import static methodsforhelp.Utils.checkingStringToBeOnlyLetters;
import methodsforhelp.colour;
import selection.SelectCourses;

/**
 *
 * @author Nasos
 */
public class EditCourse {

    private static Scanner scanner = new Scanner(System.in);

    private static void editCourse() {

        System.out.println(colour.TEXT_GREEN + "Please Select Course for edit." + colour.TEXT_RESET);
        Course c = SelectCourses.selectOneCourse(Course.getCoursesList());

        boolean quit = false;
        while (!quit) {
            System.out.println(c);
            System.out.println(colour.TEXT_GREEN + "What you want to update? Press:" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_PURPLE + "0)To go back" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "1)Title" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "2)Stream" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "3)Type" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "4)Strat Date" + colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN + "5)End Date" + colour.TEXT_RESET);
            System.out.println("");
            int choice = Utils.checkingIntegers(0, 6);

            switch (choice) {
                case 1:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(c.getTitle());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give Course Title : " + colour.TEXT_RESET);
                    c.setTitle(scanner.nextLine());
                    break;
                case 2:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(c.getStream());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give course Stream : " + colour.TEXT_RESET);
                    c.setStream(checkingStringToBeOnlyLetters());
                    break;
                case 3:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(c.getType());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give Course Type : " + colour.TEXT_RESET);
                    c.setType(checkingStringToBeOnlyLetters());
                case 4:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(c.getStartDate());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give the date this course Start : " + colour.TEXT_RESET);
                    System.out.println(colour.TEXT_YELLOW + "dd-MM-yyyy" + colour.TEXT_RESET);
                    c.setStartDate(Utils.validationLocalDate());
                    break;
                case 5:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(c.getEndDate());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give the date this course Ends : " + colour.TEXT_RESET);
                    System.out.println(colour.TEXT_YELLOW + "dd-MM-yyyy" + colour.TEXT_RESET);
                    c.setEndDate(Utils.validationLocalDate());
                case 0:
                    quit = true;
                    break;
            }

            c.updateCourseTeacher();

        }
    }

    public static void menuEditCourse() {
        while (true) {
            editCourse();
            Instructions.IntsructionsSelectionBetweenOneOrTwo();
            boolean result = Utils.optionsTrueOrFalse();
            if (result) {
                System.out.println(colour.TEXT_GREEN + "Lets edit a course!" + colour.TEXT_RESET);
            } else {
                break;
            }
        }

    }
}
