/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import mainclasses.Student;
import methodsforhelp.Instructions;
import methodsforhelp.Utils;
import static methodsforhelp.Utils.checkingIntegers;
import static methodsforhelp.Utils.checkingStringToBeOnlyLetters;
import methodsforhelp.colour;
import selection.SelectStudents;

/**
 *
 * @author Nasos
 */
public class EditStudent {

    private static void editStudent() {

        System.out.println(colour.TEXT_GREEN + "Please Select Student for edit." + colour.TEXT_RESET);
        Student s = SelectStudents.selectOneStudent(Student.getStudentsList());

        boolean quit = false;
        while (!quit) {
            System.out.println(s);
            System.out.println(colour.TEXT_GREEN +"What you want to update? Press:"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_PURPLE+"0)To go back"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN+"1)First Name"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN+"2)Last Name"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN+"3)Date Of Birth"+ colour.TEXT_RESET);
            System.out.println(colour.TEXT_CYAN+"4)Tuition Fees"+ colour.TEXT_RESET);
            System.out.println("");
            int choice = Utils.checkingIntegers(0, 5);

            switch (choice) {
                case 1:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(s.getFirstName());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give new first name for Student : " + colour.TEXT_RESET);
                    s.setFirstName(checkingStringToBeOnlyLetters());
                    break;
                case 2:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(s.getLastName());  
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give new last name for Student : " + colour.TEXT_RESET);
                    s.setLastName(checkingStringToBeOnlyLetters());
                    break;
                case 3:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(s.getDateOfBirth());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give the new Date of Birth : " + colour.TEXT_RESET);
                    System.out.println(colour.TEXT_YELLOW + "dd-MM-yyyy" + colour.TEXT_RESET);
                    s.setDateOfBirth(Utils.validationLocalDate());
                case 4:
                    System.out.println(colour.TEXT_GREEN +"Previous value : "+ colour.TEXT_RESET);
                    System.out.println(s.getTuitionFees());
                    System.out.println("");
                    System.out.println(colour.TEXT_CYAN + "Give tuition Fees : " + colour.TEXT_RESET);
                    s.setTuitionFees(checkingIntegers());
                    break;
                case 0:
                    quit = true;
                    break;
            }

            s.updateStudent();

        }
    }

    public static void menuEditStudent() {
        while (true) {
           editStudent();
            Instructions.IntsructionsSelectionBetweenOneOrTwo();
            boolean result = Utils.optionsTrueOrFalse();
            if (result) {
                System.out.println(colour.TEXT_GREEN + "Lets edit a student!" + colour.TEXT_RESET);
            } else {
                break;
            }
        }

    }
}
