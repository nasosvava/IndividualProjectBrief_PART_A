/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import database.TableCourses;
import database.TableStudents;
import java.util.Scanner;
import mainclasses.Assignments;
import mainclasses.Course;
import mainclasses.Student;
import mainclasses.Teacher;
import methodsforhelp.colour;
import selection.SelectAssignments;
import selection.SelectCourses;
import selection.SelectStudents;
import selection.SelectTeachers;

/**
 *
 * @author Nasos
 */
public class DeleteFromDao {

    public static void deleteTeacher() {

        System.out.println(colour.TEXT_GREEN + "Please Select Assignmet for Delete." + colour.TEXT_RESET);
        Teacher t = SelectTeachers.selectOneTeacher(Teacher.getTeacherList());
        t.deleteCourseTeacher();
        t.deleteTeacher();
        System.out.println(colour.TEXT_YELLOW + "Teacher Successfully Deleted" + colour.TEXT_RESET);
    }

    public static void deleteTeacherFromCourse() {
        System.out.println(colour.TEXT_GREEN + "Please Select Teacher." + colour.TEXT_RESET);
        Teacher t = SelectTeachers.selectOneTeacher(Teacher.getTeacherList());
        System.out.println(colour.TEXT_GREEN + "Please Select Course." + colour.TEXT_RESET);
        Course c = SelectCourses.selectOneCourse(Course.getCoursesList());
        TableCourses.deleteCourseTeacher(t.getId(), c.getId());
        System.out.println(colour.TEXT_YELLOW + "Column Successfully Deleted" + colour.TEXT_RESET);
    }

    public static void deleteStudent() {
        System.out.println(colour.TEXT_GREEN + "Please Select Student for Delete." + colour.TEXT_RESET);
        Student s = SelectStudents.selectOneStudent(Student.getStudentsList());
        s.deleteStudentGrade();
        s.deleteStudentCourse();
        s.deleteStudent();
        System.out.println(colour.TEXT_YELLOW + "Student Successfully Deleted" + colour.TEXT_RESET);
    }

    public static void deleteStudentFromCourse() {
        System.out.println(colour.TEXT_GREEN + "Please Select Student." + colour.TEXT_RESET);
        Student s = SelectStudents.selectOneStudent(Student.getStudentsList());
        System.out.println(colour.TEXT_GREEN + "Please Select Course." + colour.TEXT_RESET);
        Course c = SelectCourses.selectOneCourse(Course.getCoursesList());
        TableCourses.deleteCourseStudent(s.getId(), c.getId());
        System.out.println(colour.TEXT_YELLOW + "Column Successfully Deleted" + colour.TEXT_RESET);
    }

    public static void deleteCourse() {
        System.out.println(colour.TEXT_GREEN + "Please Select Course for delete." + colour.TEXT_RESET);
        Course c = SelectCourses.selectOneCourse(Course.getCoursesList());
        c.deleteCourseAssignment();
        c.deleteCourseGrade();
        c.deleteCourseStudent();
        c.deleteCourseTeacher();
        c.deleteCourse();
        System.out.println(colour.TEXT_YELLOW + "Course Successfully Deleted" + colour.TEXT_RESET);
    }

    public static void deleteAssignment() {
        System.out.println(colour.TEXT_GREEN + "Please Select Assignmet for Delete." + colour.TEXT_RESET);
        Assignments a = SelectAssignments.selectOneAssignment(Assignments.getAssignmentsList());
        a.deleteAssignmentCourse();
        a.deleteAssignmentGrades();
        a.deleteAssignment();
        System.out.println(colour.TEXT_YELLOW + "Column Successfully Deleted" + colour.TEXT_RESET);
    }

    public static void deleteAssignmentFromCourse() {
        System.out.println(colour.TEXT_GREEN + "Please Select Assignmet." + colour.TEXT_RESET);
        Assignments a = SelectAssignments.selectOneAssignment(Assignments.getAssignmentsList());
        System.out.println(colour.TEXT_GREEN + "Please Select Course." + colour.TEXT_RESET);
        Course c = SelectCourses.selectOneCourse(Course.getCoursesList());
        TableCourses.deleteCourseAssignment(a.getId(), c.getId());
        System.out.println(colour.TEXT_YELLOW + "Column Successfully Deleted" + colour.TEXT_RESET);
    }

    public static void deleteGrade() {
        System.out.println(colour.TEXT_GREEN + "Please Select Course." + colour.TEXT_RESET);
        Course c = SelectCourses.selectOneCourse(Course.getCoursesList());
        System.out.println(colour.TEXT_GREEN + "Please Select Assignmet." + colour.TEXT_RESET);
        Assignments a = SelectAssignments.selectOneAssignment(Assignments.getAssignmentsList());
        System.out.println(colour.TEXT_GREEN + "Please Select Student." + colour.TEXT_RESET);
        Student s = SelectStudents.selectOneStudent(Student.getStudentsList());
        TableStudents.deleteGrade(c.getId(), s.getId(), a.getId());
    }

}
