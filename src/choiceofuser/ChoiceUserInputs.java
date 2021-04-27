/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choiceofuser;

import edit.DeleteFromDao;
import edit.EditAssignment;
import edit.EditCourse;
import edit.EditStudent;
import edit.EditTeacher;
import java.sql.SQLException;
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
    public static void choiceOfUserToDo() throws SQLException {

        boolean quit = false;
        while (!quit) {
            Instructions.instructionsChoiceOfUserToDo();
            int choice = Utils.checkingIntegers(0, 5);

            switch (choice) {
                case 1:
                    creationUserInput();
                    break;
                case 2:
                    userAddingInput();
                    break;
                case 3:
                    editContent();
                    break;
                case 4:
                    deleteContent();
                    break;
                case 5:
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
    public static void creationUserInput() throws SQLException {
        boolean quit = false;
        while (!quit) {
            Instructions.instructionsForCreation();
            int choice = Utils.checkingIntegers(0, 4);

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

    public static void editContent() throws SQLException {
        boolean quit = false;
        while (!quit) {
            Instructions.InstractionsForEdit();
            int choice = Utils.checkingIntegers(0, 4);

            switch (choice) {
                case 1:
                    EditStudent.menuEditStudent();
                    break;
                case 2:
                    EditTeacher.menuEditTeacher();
                    break;
                case 3:
                    DeleteFromDao.deleteStudentFromCourse();
                    break;
                case 4:
                    EditCourse.menuEditCourse();
                    break;
                case 0:
                    quit = true;
                    break;
            }

        }
    }

    public static void deleteContent() {
        boolean quit = false;
        while (!quit) {
            Instructions.InstractionsForDelete();
            int choice = Utils.checkingIntegers(0, 8);

            switch (choice) {
                case 1:
                    DeleteFromDao.deleteStudent();
                    break;
                case 2:
                    DeleteFromDao.deleteTeacher();
                    break;
                case 3:
                    DeleteFromDao.deleteAssignment();
                    break;
                case 4:
                    DeleteFromDao.deleteCourse();
                    break;
                case 5:
                    DeleteFromDao.deleteStudentFromCourse();
                    break;
                case 6:
                    DeleteFromDao.deleteTeacherFromCourse();
                    break;
                case 7:
                    DeleteFromDao.deleteAssignmentFromCourse();
                    break;
                case 8:
                    DeleteFromDao.deleteGrade();
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
            int choice = Utils.checkingIntegers(0, 4);

            switch (choice) {
                case 1:
                    if (Student.getStudentsList().isEmpty() || Course.getCoursesList().isEmpty()) {
                        System.out.println(colour.TEXT_RED + "Something is missing!" + colour.TEXT_RESET + colour.TEXT_YELLOW + "\nCheck your CourseList and your StudentList!" + colour.TEXT_RESET);
                    } else {
                        Adding.addStudentsInCourse();
                    }
                    break;
                case 2:
                    if (Teacher.getTeacherList().isEmpty() || Course.getCoursesList().isEmpty()) {
                        System.out.println(colour.TEXT_RED + "Something is missing!" + colour.TEXT_RESET + colour.TEXT_YELLOW + "\nCheck your CourseList and your TeacherList!" + colour.TEXT_RESET);
                    } else {
                        Adding.addTeachersInCourse();
                    }
                    break;
                case 3:
                    if (Assignments.getAssignmentsList().isEmpty() || Course.getCoursesList().isEmpty()) {
                        System.out.println(colour.TEXT_RED + "Something is missing!" + colour.TEXT_RESET + colour.TEXT_YELLOW + "\nCheck your CourseList and your AssignmentList!" + colour.TEXT_RESET);
                    } else {
                        Adding.addAssignmentsInCourse();
                    }
                    break;
                case 4:
                    if (Assignments.getAssignmentsList().isEmpty() || Course.getCoursesList().isEmpty() || Student.getStudentsList().isEmpty()) {
                        System.out.println(colour.TEXT_RED + "Something is missing!" + colour.TEXT_RESET + colour.TEXT_YELLOW + "\nCheck your CourseList , your AssignmentList and StudentList!" + colour.TEXT_RESET);
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
            int choice = Utils.checkingIntegers(0, 11);

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
