/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import mainclasses.Teacher;
import methodsforhelp.Instructions;
import methodsforhelp.Utils;
import static methodsforhelp.Utils.checkingStringToBeOnlyLetters;
import methodsforhelp.colour;
import selection.SelectTeachers;

/**
 *
 * @author Nasos
 */
public class EditTeacher {
        private static void editTeacher() {

        System.out.println(colour.TEXT_GREEN + "Please Select Student for edit." + colour.TEXT_RESET);
        Teacher t = SelectTeachers.selectOneTeacher(Teacher.getTeacherList());

        boolean quit = false;
        while (!quit) {
            System.out.println(t);
            System.out.println(colour.TEXT_GREEN +"What you want to update? Press:"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_PURPLE+"0)To go back"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN+"1)First Name"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN+"2)Last Name"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN+"3)Subject"+ colour.TEXT_RESET);
            System.out.println("");
            
            int choice = Utils.checkingIntegers(0, 5);

            switch (choice) {
                case 1:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(t.getFirstName());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give the first name of the  Teacher : " + colour.TEXT_RESET);
                    t.setFirstName(checkingStringToBeOnlyLetters());
                    break;
                case 2:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(t.getLastName());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give the last name of the  Teacher : " + colour.TEXT_RESET);
                    t.setLastName(checkingStringToBeOnlyLetters());
                    break;
                case 3:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(t.getTeachingCourses());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN +"Give Teaching Subject of the Teacher : "+ colour.TEXT_RESET);
                    t.setTeachingCourses(checkingStringToBeOnlyLetters());
                case 0:
                    quit = true;
                    break;
            }

            t.updateTeacher();

        }
    }

    public static void menuEditTeacher() {
        while (true) {
            editTeacher();
            Instructions.IntsructionsSelectionBetweenOneOrTwo();
            boolean result = Utils.optionsTrueOrFalse();
            if (result) {
                System.out.println(colour.TEXT_GREEN + "Lets edit a teacher!" + colour.TEXT_RESET);
            } else {
                break;
            }
        }

    }
}
