/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodsforhelp;

/**
 *
 * @author Nasos
 */
public class Instructions {

    public static void InstructionsForSynthetic() {
        System.out.println(colour.TEXT_GREEN + "Welcome to my Project? Press" + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t To continow with the program press any button." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_PURPLE + "\t 'Exit'-To stop the program." + colour.TEXT_RESET);

    }

    public static void instructionsChoiceOfUserToDo() {
        System.out.println(colour.TEXT_GREEN + "Choose between one of the options below." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_PURPLE + "\t 0-To go back." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 1-To start creating." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 2-To start adding to courses : Student , Teachers , Assignments." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 3-To edit your data" + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 4-To delete your data" + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 5-To print your data" + colour.TEXT_RESET);
    }

    public static void instructionsForCreation() {
        System.out.println(colour.TEXT_GREEN + "Choose between one of the options below." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_PURPLE + "\t 0-To go back." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 1-Create Student." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 2-Create Teacher." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 3-Create Assignments." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 4-Create Course" + colour.TEXT_RESET);
    }

    public static void instructionsForUserAddingInput() {
        System.out.println(colour.TEXT_GREEN + "Choose between one of the options below." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_PURPLE + "\t 0-To go back." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 1-To add student to courses." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 2-To add teacher to courses." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 3-To add assignment to courses." + colour.TEXT_RESET);
        System.out.println((colour.TEXT_CYAN + "\t 4-To add grade to student." + colour.TEXT_RESET));
    }

    public static void instructionsForPrinting() {
        System.out.println(colour.TEXT_GREEN + "Choose between one of the options below." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_PURPLE + "\t 0-To go back." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 1-Print all the students of the school." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 2-Print all the teachers of the school." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 3-Print all the assignments of the school." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 4-Print all the course of the school." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 5-Print all the student per course." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 6-Print all the teacher per course." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 7-Print all the assignment per course." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 8-Print all the assignment per student." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 9-Print all studetn with more than one course." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 10-Give date for the assignment" + colour.TEXT_YELLOW + "(like : dd-mm-yyyy)" + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 11-Print student assignmnetns wiht their grades." + colour.TEXT_RESET);
    }

    public static void IntsructionsSelectionBetweenOneOrTwo() {

        System.out.println(colour.TEXT_GREEN + "Do you want more? Press" + colour.TEXT_RESET);
        System.out.println(colour.TEXT_YELLOW + "\t 1-For yes." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_RED + "\t 2-For no." + colour.TEXT_RESET);

    }

    public static void InstractionsForEdit() {
        System.out.println(colour.TEXT_GREEN + "Choose between one of the options below." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_PURPLE + "\t 0-To go back." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 1-Edit Student." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 2-Edit Teacher." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 3-Edit Assignments." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 4-Edit Course" + colour.TEXT_RESET);
        System.out.println("");
    }

    public static void InstractionsForDelete() {
        System.out.println(colour.TEXT_GREEN + "Choose between one of the options below." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_PURPLE + "\t 0-To go back." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 1-Delete Student." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 2-Delete Teacher." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 3-Delete Assignments." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 4-Delete Course" + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 5-Delete Student from Course." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 6-Delete Teacher from Course." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 7-Delete Assignments." + colour.TEXT_RESET);
        System.out.println(colour.TEXT_CYAN + "\t 8-Delete Course" + colour.TEXT_RESET);
        System.out.println("");
    }
}
