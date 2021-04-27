/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selection;

import mainclasses.Teacher;
import java.util.ArrayList;
import java.util.Scanner;
import methodsforhelp.Instructions;
import methodsforhelp.Utils;
import methodsforhelp.colour;

/**
 *
 * @author Nasos
 */
public class SelectTeachers {

    private static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Teacher> selectManyTeachers(ArrayList<Teacher> teacherList) {

        ArrayList<Teacher> selectedTeachers = new ArrayList();

        boolean quit = false;

        while (quit == false) {
            if (teacherList.isEmpty()) {
                System.out.println(colour.TEXT_RED+"You dont have more Teachers to Add.Make your next Choice!"+colour.TEXT_RESET);
                quit = true;
            } else {
                int counter = 1;

                System.out.println(colour.TEXT_YELLOW+"Select a Teacher"+colour.TEXT_RESET);

                for (Teacher teacher : teacherList) {
                    System.out.println(counter + " " + teacher.getFirstName() + " " + teacher.getLastName());
                    counter++;
                }
                int choice = Utils.checkingIntegers(1, teacherList.size());

                selectedTeachers.add(teacherList.get(choice - 1));
                teacherList.remove(choice - 1);

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
        return selectedTeachers;

    }

    public static Teacher selectOneTeacher(ArrayList<Teacher> teacherList) {
        System.out.println(colour.TEXT_YELLOW+"Choose one teacher!"+colour.TEXT_RESET);
        int counter = 1;
        for (Teacher teacher : teacherList) {
            System.out.println(counter + " " + teacher.getFirstName() + " " + teacher.getLastName());
            counter++;
        }
        int i = Utils.checkingIntegers(1, teacherList.size());
        Teacher teacher = teacherList.get(i - 1);
        return teacher;
    }

}
