/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choiceofuser;

import java.sql.SQLException;
import mainclasses.Student;
import mainclasses.Course;
import mainclasses.Teacher;
import mainclasses.Assignments;
import java.util.Scanner;
import methodsforhelp.Instructions;
import methodsforhelp.Utils;
import methodsforhelp.colour;
import static methodsforhelp.Utils.checkingIntegers;
import static methodsforhelp.Utils.checkingStringToBeOnlyLetters;
import selection.SelectCourses;
import selection.SelectStudents;

/**
 *
 * @author Nasos
 */
public class Creation {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Create Student and put him in the list.
     */
    private static void createStudent() throws SQLException {
        Student newStudent = new Student();

        while (true) {
            System.out.println(colour.TEXT_GREEN + "Give Student Details" + colour.TEXT_RESET);

            System.out.println(colour.TEXT_CYAN + "Give the first name of the new Student : " + colour.TEXT_RESET);
            newStudent.setFirstName(checkingStringToBeOnlyLetters());

            System.out.println(colour.TEXT_CYAN + "Give the last name of the new Student : " + colour.TEXT_RESET);
            newStudent.setLastName(checkingStringToBeOnlyLetters());

            System.out.println(colour.TEXT_CYAN + "Give Date of Birth : " + colour.TEXT_RESET);
            System.out.println(colour.TEXT_YELLOW + "dd-MM-yyyy" + colour.TEXT_RESET);
            newStudent.setDateOfBirth(Utils.validationLocalDate());

            System.out.println(colour.TEXT_CYAN + "Give tuition Fees : " + colour.TEXT_RESET);
            newStudent.setTuitionFees(checkingIntegers());

            if (!Student.existStudent(newStudent)) {            //Here we Check is this alredy exist!
                Student.saveStudent(newStudent);
                break;
            } else {
                System.out.println(colour.TEXT_RED + "This Student already exists! Give a new Student!" + colour.TEXT_RESET);
            }
        }
    }

    /**
     * Create Teacher and put him in the list.
     */
    private static void createTeacher() throws SQLException {

        Teacher newTeacher = new Teacher();
        while (true) {
            System.out.println(colour.TEXT_GREEN + "Give Teacher Details" + colour.TEXT_RESET);

            System.out.println(colour.TEXT_CYAN + "Give the first name of the new Teacher : " + colour.TEXT_RESET);
            newTeacher.setFirstName(checkingStringToBeOnlyLetters());

            System.out.println(colour.TEXT_CYAN + "Give the last name of the new Teacher : " + colour.TEXT_RESET);
            newTeacher.setLastName(checkingStringToBeOnlyLetters());

            System.out.println(colour.TEXT_CYAN + "Give Courses he is gonna Teach : " + colour.TEXT_RESET);
            newTeacher.setTeachingCourses(scanner.nextLine());

            if (!Teacher.existTeacher(newTeacher)) {
                Teacher.saveTeacher(newTeacher);
                break;                                              //Here we Check is this alredy exist!
            } else {
                System.out.println(colour.TEXT_RED + "This teacher already exists! Give a new Teacher!" + colour.TEXT_RESET);
            }
        }
    }

    /**
     * Create course and put it in the list.
     */
    private static void createCourse() throws SQLException {

        Course newCourse = new Course();

        while (true) {

            System.out.println(colour.TEXT_GREEN + "Give Course Details" + colour.TEXT_RESET);

            System.out.println(colour.TEXT_CYAN + "Give Course Title : " + colour.TEXT_RESET);
            newCourse.setTitle(scanner.nextLine());

            System.out.println(colour.TEXT_CYAN + "Give course Stream : " + colour.TEXT_RESET);
            newCourse.setStream(checkingStringToBeOnlyLetters());

            System.out.println(colour.TEXT_CYAN + "Give Course Type : " + colour.TEXT_RESET);
            newCourse.setType(checkingStringToBeOnlyLetters());

            System.out.println(colour.TEXT_CYAN + "Give the date this course Start : " + colour.TEXT_RESET);
            System.out.println(colour.TEXT_YELLOW + "dd-MM-yyyy" + colour.TEXT_RESET);
            newCourse.setStartDate(Utils.validationLocalDate());

            System.out.println(colour.TEXT_CYAN + "Give the date this course Ends : " + colour.TEXT_RESET);
            System.out.println(colour.TEXT_YELLOW + "dd-MM-yyyy" + colour.TEXT_RESET);
            newCourse.setEndDate(Utils.validationLocalDate());

            if (!Course.existCourse(newCourse)) {
                Course.saveCourse(newCourse);
                break;                                      //Here we Check is this alredy exist!
            } else {
                System.out.println(colour.TEXT_RED + "This course already exists! Give a new Course!" + colour.TEXT_RESET);
            }

        }

    }



    /**
     * Create course and put it in the list.
     */
    private static void createAssignment() throws SQLException {

        Assignments newAssignment = new Assignments();

        while (true) {

            System.out.println(colour.TEXT_GREEN + "Give Assignment Details!" + colour.TEXT_RESET);

            System.out.println(colour.TEXT_CYAN + "Give Assignment Title :" + colour.TEXT_RESET);
            newAssignment.setTitle(scanner.nextLine());

            System.out.println(colour.TEXT_CYAN + "Give Assignment Description : " + colour.TEXT_RESET);
            newAssignment.setDescription(scanner.nextLine());

            System.out.println(colour.TEXT_CYAN + "Give Assignment Sub Date Time : " + colour.TEXT_RESET);
            System.out.println(colour.TEXT_YELLOW + "dd-MM-yyyy" + colour.TEXT_RESET);
            newAssignment.setSubDateTime(Utils.validationLocalDate());

            System.out.println(colour.TEXT_CYAN + "Give Assignment Oral Mark : " + colour.TEXT_RESET);
            newAssignment.setOralMark(checkingIntegers());

            System.out.println(colour.TEXT_CYAN + "Give Assignment Total Mark : " + colour.TEXT_RESET);
            newAssignment.setTotalMark(checkingIntegers());

            if (!Assignments.existAssignemnt(newAssignment)) {
                Assignments.saveAssignment(newAssignment);
                break;                                          //Here we Check is this alredy exist!
            } else {
                System.out.println(colour.TEXT_RED + "This Assignment already exists! Give a new Assignemnt" + colour.TEXT_RESET);
            }
        }

    }

    static void menuCreationStudent() throws SQLException {
        while (true) {
            Creation.createStudent();
            Instructions.IntsructionsSelectionBetweenOneOrTwo();
            boolean result = Utils.optionsTrueOrFalse();
            if (result) {
                System.out.println(colour.TEXT_GREEN + "Lets make the new Student!" + colour.TEXT_RESET);
            } else {
                break;
            }
        }

    }

    static void menuCreationTeacher() throws SQLException {
        while (true) {
            Creation.createTeacher();
            Instructions.IntsructionsSelectionBetweenOneOrTwo();
            boolean result = Utils.optionsTrueOrFalse();
            if (result) {
                System.out.println(colour.TEXT_GREEN + "Lets make the new Teacher!" + colour.TEXT_RESET);
            } else {
                break;
            }
        }

    }

    static void menuCreationAssignment() throws SQLException {
        while (true) {
            Creation.createAssignment();
            Instructions.IntsructionsSelectionBetweenOneOrTwo();
            boolean result = Utils.optionsTrueOrFalse();
            if (result) {
                System.out.println(colour.TEXT_GREEN + "Lets make the new Assignment!" + colour.TEXT_RESET);
            } else {
                break;
            }
        }

    }

    static void menuCreationCourse() throws SQLException {
        while (true) {
            Creation.createCourse();
            Instructions.IntsructionsSelectionBetweenOneOrTwo();
            boolean result = Utils.optionsTrueOrFalse();
            if (result) {
                System.out.println(colour.TEXT_GREEN + "Lets make the new Course!" + colour.TEXT_RESET);
            } else {
                break;
            }
        }

    }

}
