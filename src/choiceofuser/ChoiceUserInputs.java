/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choiceofuser;

import methodsforhelp.Utils;
import methodsforhelp.Instructions;
import mainclasses.Student;
import mainclasses.Course;
import mainclasses.Teacher;
import mainclasses.Assignments;
import methodsforhelp.colour;
import projecta.Printing;

/**
 *
 * @author Nasos
 */
public class ChoiceUserInputs {

 /**
  * Menu for Creation Or Adding Or Printing.
  */
    public static void choiceOfUserToDo() {
        
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
    /**
     * Menu for Creation.
     */
    public static void creationUserInput() {
        boolean quit = false;
        while (!quit) {
            Instructions.instructionsForCreation();
            int choice = Utils.checkingIntegers(0,4);
            
                switch (choice) {
                    case 1:
                        Creation.menuCreationStudent();
                        break;
                    case 2:
                        Creation.menuCreationTeacher();
                        break;
                    case 3:
                        Creation.menuCreationAssignment();
                        break;
                    case 4:
                        Creation.menuCreationCourse();
                        break;
                    case 0:
                        quit = true;
                        break;
                }

            
        }
    }
    
    /**
     * Menu for adding.
     */
    public static void userAddingInput() {
        boolean quit = false;
        while (!quit) {
            Instructions.instructionsForUserAddingInput();
            int choice = Utils.checkingIntegers(0,4);
            
                switch (choice) {
                    case 1:
                        if (Student.getStudentsList().isEmpty() || Course.getCoursesList().isEmpty()) {
                            System.out.println(colour.TEXT_RED+"Something is missing!"+colour.TEXT_RESET+colour.TEXT_YELLOW+"\nCheck your CourseList and your StudentList!"+colour.TEXT_RESET);
                        } else {
                            Adding.addStudentsInCourse();
                        }
                        break;
                    case 2:
                        if (Teacher.getTeacherList().isEmpty() || Course.getCoursesList().isEmpty()) {
                            System.out.println(colour.TEXT_RED+"Something is missing!"+colour.TEXT_RESET+colour.TEXT_YELLOW+"\nCheck your CourseList and your TeacherList!"+colour.TEXT_RESET);
                        } else {
                            Adding.addTeachersInCourse();
                        }
                        break;
                    case 3:
                        if (Assignments.getAssignmentsList().isEmpty() || Course.getCoursesList().isEmpty()) {
                            System.out.println(colour.TEXT_RED+"Something is missing!"+colour.TEXT_RESET+colour.TEXT_YELLOW+"\nCheck your CourseList and your AssignmentList!"+colour.TEXT_RESET);
                        } else {
                            Adding.addAssignmentsInCourse();
                        }
                        break;
                    case 4:
                            if (Assignments.getAssignmentsList().isEmpty() || Course.getCoursesList().isEmpty() || Student.getStudentsList().isEmpty()) {
                            System.out.println(colour.TEXT_RED+"Something is missing!"+colour.TEXT_RESET+colour.TEXT_YELLOW+"\nCheck your CourseList , your AssignmentList and StudentList!"+colour.TEXT_RESET);
                        } else {
                             Adding.addGradeToStudent();  
                            }                      
                        break;
                    case 0:
                        quit = true;
                        break;
                }

            
        }

    }
    /**
     * Menu for printing.
     */
    public static void printingLists() {
        boolean quit = false;
        while (!quit) {
            Instructions.instructionsForPrinting();
            int choice = Utils.checkingIntegers(0,11);
            
                switch (choice) {
                    case 1:
                        Printing.printAllStudents();
                        break;
                    case 2:
                        Printing.printAllTeachers();
                        break;
                    case 3:
                        Printing.printAllAssignments();
                        break;
                    case 4:
                        Printing.printAllCourses();
                        break;
                    case 5:
                        Printing.printStudentPerCourse();
                        break;
                    case 6:
                        Printing.printAllTeacherPerCourse();
                        break;
                    case 7:
                        Printing.printAllAssignmentsPerCourse();
                        break;
                    case 8:
                        Printing.printAssignmentsPerStudent();
                        break;
                    case 9:
                        Printing.studentInMoreThanOneCourse();
                        break;
                    case 10:
                        Printing.studentsGiveWeeksgAssignments();
                        break;
                    case 11:
                        Printing.printGradesOfStudents();
                        break;
                    case 0:
                        quit = true;
                        break;
                }

            
        }
    }

}
