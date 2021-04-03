/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selection;

import mainclasses.Student;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import methodsforhelp.Instructions;
import methodsforhelp.Utils;
import methodsforhelp.colour;

/**
 *
 * @author Nasos
 */
public class SelectStudents {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Select many Students for Student List.
     * @param studentList
     * @return ArrayList with selected Student
     */
    public static ArrayList<Student> selectManyStudents(ArrayList<Student> studentList) {
        boolean quit = false;

        ArrayList<Student> selectedStudents = new ArrayList();

        while (quit == false) {
            if (studentList.size() == 0) {
                System.out.println(colour.TEXT_RED + "You dont have more Students to add!" + colour.TEXT_RESET);
                System.out.println("Make your new Choice!");
                quit = true;
            } else {

                int counter = 1;

                System.out.println(colour.TEXT_YELLOW + "Select a Student" + colour.TEXT_RESET);

                for (Student student : studentList) {
                    System.out.println(counter + " " + student.getFirstName() + " " + student.getLastName());
                    counter++;
                }
                int choice = Utils.checkingIntegers(1, studentList.size());

                selectedStudents.add(studentList.get(choice - 1));
                studentList.remove(choice - 1);

                Instructions.IntsructionsSelectionBetweenOneOrTwo();

                boolean input = Utils.optionsTrueOrFalse();

                if (input == true) {
                    System.out.println(colour.TEXT_YELLOW + "Select the next one!" + colour.TEXT_RESET);
                } else {
                    System.out.println(colour.TEXT_RED + "You finished." + colour.TEXT_RESET);
                    quit = true;
                }
            }
        }

        return selectedStudents;
    }
    /**
     * Select one Student for Student List.
     * @param studentList
     * @return Student
     */
    public static Student selectOneStudent(ArrayList<Student> studentList) {
        System.out.println(colour.TEXT_YELLOW + "Choose one student!" + colour.TEXT_RESET);
        int counter = 1;
        for (Student student : studentList) {
            System.out.println(counter + " " + student.getFirstName() + " " + student.getLastName());
            counter++;
        }
        int i = Utils.checkingIntegers(1, studentList.size());
        Student student = studentList.get(i - 1);
        return student;
    }
}
