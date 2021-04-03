/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecta;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import mainclasses.Student;
import mainclasses.Course;
import mainclasses.Teacher;
import mainclasses.Assignments;
import java.util.ArrayList;
import java.util.Locale;
import mainclasses.Grades;
import methodsforhelp.Utils;
import methodsforhelp.colour;
import selection.SelectAssignments;
import selection.SelectCourses;

/**
 *
 * @author Nasos
 */
public class Printing {

    /**
     * Printing all students of the school with checks.
     */
    public static void printAllStudents() {
        int counter = 1;
        if (Student.getStudentsList().isEmpty()) { //Checks if we have studets in the school.
            System.out.println(colour.TEXT_RED + "You dont have Students." + colour.TEXT_RESET);
        } else {
            for (Student student : Student.getStudentsList()) {
                System.out.println(counter + ") " + student + "\n");
                counter++;
            }
        }
    }

    /**
     * Print all Courses of the school with checks.
     */
    public static void printAllCourses() {
        int counter = 1;
        if (Course.getCoursesList().isEmpty()) { //Checks if we have courses in the school.
            System.out.println(colour.TEXT_RED + "You dont have Courses." + colour.TEXT_RESET);
        } else {
            for (Course course : Course.getCoursesList()) {
                System.out.println(counter + ") " + course + "\n");
                counter++;
            }
        }
    }

    /**
     * Print all Teacher of the school with checks.
     */
    public static void printAllTeachers() {
        int counter = 1;
        if (Teacher.getTeacherList().isEmpty()) { //Checks if we have teacher in the school.
            System.out.println(colour.TEXT_RED + "You dont teachers." + colour.TEXT_RESET);
        } else {
            for (Teacher teacher : Teacher.getTeacherList()) {
                System.out.println(counter + ") " + teacher + "\n");
                counter++;
            }
        }
    }

    /**
     * Print all Assignments of the school with checks.
     */
    public static void printAllAssignments() {
        int counter = 1;
        if (Assignments.getAssignmentsList().isEmpty()) { //Checks if we have assignments in the school.
            System.out.println(colour.TEXT_RED + "You dont have assignments." + colour.TEXT_RESET);
        } else {
            for (Assignments assignment : Assignments.getAssignmentsList()) {
                System.out.println(counter + ") " + assignment + "\n");
                counter++;
            }
        }
    }

    /**
     * Print Student per Course.
     */
    public static void printStudentPerCourse() {
        if (Course.getCoursesList().isEmpty() || Student.getStudentsList().isEmpty()) { //Checks if we have students or courses with in our course.
            System.out.println(colour.TEXT_RED + "Something is missing." + colour.TEXT_RESET
                    + colour.TEXT_YELLOW + "\nCheck you Students and your Courses." + colour.TEXT_RESET);
        } else {
            for (Course course : Course.getCoursesList()) {
                if (course.getStudentPerCourse().isEmpty()) { //checks if the courses has students inside.
                    System.out.println(course.getCourseTitle() + " " + course.getStream() + colour.TEXT_RED + " : This Course has no students." + colour.TEXT_RESET);

                } else {
                    int counter = 1;
                    System.out.println(course.getCourseTitle() + " " + course.getStream() + " :");
                    System.out.println("");
                    for (Student student : course.getStudentPerCourse()) {

                        System.out.println(counter + ")    " + student.getFirstName() + " " + student.getLastName());
                        counter++;
                    }
                    System.out.println("");
                }
            }
        }
    }

    /**
     * Print Teacher per Course.
     */
    public static void printAllTeacherPerCourse() {
        if (Course.getCoursesList().isEmpty() || Teacher.getTeacherList().isEmpty()) { //Checks if we have teachers or courses with in our course.
            System.out.println(colour.TEXT_RED + "Something is missing." + colour.TEXT_RESET
                    + colour.TEXT_YELLOW + "\nCheck you Teachers and your Courses." + colour.TEXT_RESET);
        } else {
            for (Course course : Course.getCoursesList()) {
                if (course.getTeacherPerCourse().isEmpty()) { //checks if the teachers has students inside.
                    System.out.println(course.getCourseTitle() + " " + course.getStream() + colour.TEXT_RED + " : This Course has no Teachers." + colour.TEXT_RESET);
                } else {
                    int counter = 1;
                    System.out.println(course.getCourseTitle() + " " + course.getStream() + " :");
                    System.out.println("");
                    for (Teacher teacher : course.getTeacherPerCourse()) {
                        System.out.println(counter + ")    " + teacher.getFirstName() + " " + teacher.getLastName());
                        counter++;
                    }
                    System.out.println("");
                }
            }
        }
    }

    /**
     * Print Assignment per Course.
     */
    public static void printAllAssignmentsPerCourse() {
        if (Course.getCoursesList().isEmpty() || Assignments.getAssignmentsList().isEmpty()) {//Checks if we have assignmetns or courses with in our course.
            System.out.println(colour.TEXT_RED + "Something is missing." + colour.TEXT_RESET
                    + colour.TEXT_YELLOW + "\nCheck you Assingments and your Courses." + colour.TEXT_RESET);
        } else {
            for (Course course : Course.getCoursesList()) {
                if (course.getAssignmentPerCourse().isEmpty()) { //checks if the courses has assignmetns inside.
                    System.out.println(course.getCourseTitle() + " " + course.getStream() + colour.TEXT_RED + " : This Course has no Assignments." + colour.TEXT_RESET);
                } else {
                    int counter = 1;
                    System.out.println(course.getCourseTitle() + " " + course.getStream() + " :");
                    System.out.println("");
                    for (Assignments assignment : course.getAssignmentPerCourse()) {
                        System.out.println(counter + ")    " + assignment.getTitle() + " " + assignment.getDescription());
                        counter++;
                    }
                    System.out.println("");
                }
            }
        }
    }

    /**
     * Print Assignment per Student.
     */
    public static void printAssignmentsPerStudent() {
        if (Student.getStudentsList().isEmpty() || Assignments.getAssignmentsList().isEmpty()) {//Checks if we have assignmetns or students with in our course.
            System.out.println(colour.TEXT_RED + "Something is missing." + colour.TEXT_RESET
                    + colour.TEXT_YELLOW + "\nCheck you Assingments and your Courses." + colour.TEXT_RESET);
        } else {
            for (Student student : Student.getStudentsList()) {
                int counter = 1;
                if (student.getAllAssignments().isEmpty()) {
                    System.out.println("");
                    System.out.println(student.getFirstName() + " " + student.getLastName() + colour.TEXT_RED + " :This student has not assignments." + colour.TEXT_RESET);
                } else {
                    System.out.println("");
                    System.out.println(student.getFirstName() + " " + student.getLastName() + " has this assignments : ");

                }
                for (Assignments assingment : student.getAllAssignments()) {
                    System.out.println(counter + ") " + assingment.getTitle());

                    counter++;
                }
            }
            System.out.println("");
        }
    }

    /**
     * Print all Students that belongs to more than one Course.
     */
    public static void studentInMoreThanOneCourse() {
        if (Course.getCoursesList().isEmpty() || Student.getStudentsList().isEmpty()) {
            System.out.println(colour.TEXT_RED + "Something is missing." + colour.TEXT_RESET
                    + colour.TEXT_YELLOW + "\nCheck you Students and your Courses." + colour.TEXT_RESET);
        } else {
            ArrayList<Student> studentWithMoreCourse = new ArrayList();
            for (Student student : Student.getStudentsList()) {
                if (student.getCourses().size() > 1) {
                    studentWithMoreCourse.add(student);
                }

            }

            for (int i = 0; i < studentWithMoreCourse.size(); i++) {
                System.out.println(studentWithMoreCourse.get(i) + "\n");

            }
        }

    }

    /**
     * Date and it should output a list of students who need to submit one or
     * more assignments on the same calendar week as the given date.
     */
    public static void studentsGiveWeeksgAssignments() {
        if (Assignments.getAssignmentsList().size() > 0) {
            System.out.println(colour.TEXT_GREEN + "Give end date for the assignmetn." + colour.TEXT_RESET);
            LocalDate date = Utils.validationLocalDate();
            TemporalField week = WeekFields.ISO.weekOfYear();
            int num = date.get(week);
            ArrayList<Assignments> assig = new ArrayList();
            for (Assignments assignment : Assignments.getAssignmentsList()) {
                if (assignment.getWeek() == num) {
                    assig.add(assignment);
                }
            }
            if (assig.size() > 0) {
                for (Assignments assignment : assig) {

                    if (assignment.getCourses().size() > 0) {
                        System.out.println(assignment.getTitle() + " :");

                        for (Course course : assignment.getCourses()) {

                            System.out.println("" + course);
                            if (course.getStudentPerCourse().size() > 0) {
                                for (Student student : course.getStudentPerCourse()) {
                                    System.out.println(student.getFirstName() + " " + student.getLastName() + "\n");
                                }
                            } else {
                                System.out.print(colour.TEXT_RED + "Course dont have students.\n" + colour.TEXT_RESET);
                            }
                        }
                    } else {
                        System.out.print(colour.TEXT_RED + "No assignment in that course.\n" + colour.TEXT_RESET);
                    }
                }
            } else {
                System.out.print(colour.TEXT_RED + "No assignments in this week.\n" + colour.TEXT_RESET);
            }
        } else {
            System.out.print(colour.TEXT_RED + "You dont have any assignments.\n" + colour.TEXT_RESET);
        }

    }

    /**
     * Print Grades of students per Assignment.
     */
    public static void printGradesOfStudents() {
        if (Course.getCoursesList().size() > 0) {
            System.out.println(colour.TEXT_GREEN + "Choose Course." + colour.TEXT_RESET);
            Course course = SelectCourses.selectOneCourse(Course.getCoursesList());
            if (course.getAssignmentPerCourse().size() > 0) {
                System.out.println(colour.TEXT_GREEN + "Choose Assignment." + colour.TEXT_RESET);
                Assignments assignment = SelectAssignments.selectOneAssignment(course.getAssignmentPerCourse());
                for (Student s : course.getStudentPerCourse()) {
                    if (s.gradeExist(assignment, course)) {
                        Grades grade = s.getStudentGrade(course, assignment);
                        System.out.println(s.getFirstName() + " " + s.getLastName() + " \nOral Mark : " + grade.getOralMark() + " Total Mark : " + grade.getTotalMark());
                        System.out.println("");
                    } else {
                        System.out.println(s.getFirstName() + " " + s.getLastName() + " : " + colour.TEXT_RED + " no mark" + colour.TEXT_RESET);
                    }
                }
            } else {
                System.out.println(colour.TEXT_RED + "We dont have assignments." + colour.TEXT_RESET);
            }
        } else {
            System.out.println(colour.TEXT_RED + "We dont have courses." + colour.TEXT_RESET);
        }

    }

}
