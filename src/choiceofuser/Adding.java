/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choiceofuser;

import mainclasses.Assignments;
import mainclasses.Course;
import mainclasses.Student;
import mainclasses.Teacher;
import methodsforhelp.colour;
import selection.SelectAssignments;
import selection.SelectCourses;
import selection.SelectStudents;
import selection.SelectTeachers;
import java.util.ArrayList;
import methodsforhelp.Utils;

/**
 *
 * @author Nasos
 */
public class Adding {

    /**
     * This method students add student in a course.
     */
    public static void addStudentsInCourse() {

        System.out.println(colour.TEXT_GREEN + "Please Select Course" + colour.TEXT_RESET);
        Course course = SelectCourses.selectOneCourse(Course.getCoursesList());
        ArrayList<Student> allStudents = new ArrayList(Student.getStudentsList());
        if (course.getStudentPerCourse() != null) {
            for (Student student : course.getStudentPerCourse()) {
                allStudents.remove(student);
            }
        }

        ArrayList<Student> selectedStudents = SelectStudents.selectManyStudents(allStudents);

        for (Student student : selectedStudents) {
            course.addStudent(student);
        }

    }

    /**
     * This adds Teacher in a course.
     */
    public static void addTeachersInCourse() {

        System.out.println(colour.TEXT_GREEN + "Please Select Course" + colour.TEXT_RESET);
        Course course = SelectCourses.selectOneCourse(Course.getCoursesList());
        ArrayList<Teacher> allTeachers = new ArrayList(Teacher.getTeacherList());
        if (course.getStudentPerCourse() != null) {
            for (Teacher teacher : course.getTeacherPerCourse()) {
                allTeachers.remove(teacher);
            }
        }

        ArrayList<Teacher> selectedTeachers = SelectTeachers.selectManyTeachers(allTeachers);

        for (Teacher teacher : selectedTeachers) {
            course.addTeacher(teacher);
        }

    }

    /**
     * Add assignment in a course.
     */
    public static void addAssignmentsInCourse() {

        System.out.println(colour.TEXT_GREEN + "Please Select Course" + colour.TEXT_RESET);
        Course course = SelectCourses.selectOneCourse(Course.getCoursesList()); //You need to select one Course
        ArrayList<Assignments> allAssignments = new ArrayList(Assignments.getAssignmentsList());
        if (course.getStudentPerCourse() != null) {
            for (Assignments assignment : course.getAssignmentPerCourse()) {
                allAssignments.remove(assignment);
            }
        }

        ArrayList<Assignments> selectedAssignments = SelectAssignments.selectManyAssignments(allAssignments);

        for (Assignments assignment : selectedAssignments) {
            course.addAssignments(assignment);
        }

    }

    /**
     * Here we add grades in student with some checks.We check the sizes of the lists that contains Student Per Course and Assignment Per Course.
     */
    public static void addGradeToStudent() {
        System.out.println(colour.TEXT_GREEN + "Please Select Student" + colour.TEXT_RESET);
        Student student = SelectStudents.selectOneStudent(Student.getStudentsList());
        if (student.getCourses().size() > 0) {
            System.out.println(colour.TEXT_GREEN + "Choose course!" + colour.TEXT_RESET);
            Course course = SelectCourses.selectOneCourse(student.getCourses());
            if (course.getAssignmentPerCourse().size() > 0) {
                ArrayList<Assignments> unMarkAssignments = student.notMarkAssignments(course);
                if (unMarkAssignments.size() > 0) {
                    System.out.println(colour.TEXT_GREEN + "Choose the assignment!" + colour.TEXT_RESET);
                    Assignments assignment = SelectAssignments.selectOneAssignment(unMarkAssignments);
                    System.out.println("    ");
                    System.out.println(colour.TEXT_GREEN + "Give me oral Mark." + colour.TEXT_RESET);
                    int oralMark = Utils.checkingIntegers(1, assignment.getOralMark());
                    System.out.println(colour.TEXT_GREEN + "Give me total Mark." + colour.TEXT_RESET);
                    int totalMark = Utils.checkingIntegers(1, assignment.getTotalMark());
                    student.saveMark(assignment, course, oralMark, totalMark);
                } else {
                    System.out.println(colour.TEXT_RED + "No assignments to put grade." + colour.TEXT_RESET);
                }
            }else{
                System.out.println(colour.TEXT_RED +"This course has no assignments."+ colour.TEXT_RESET);
            }

        } else {
            System.out.println(colour.TEXT_RED + "Student doesnt have any Courses!" + colour.TEXT_RESET);
        }
    }
}
