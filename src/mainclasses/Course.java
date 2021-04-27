/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

import database.TableCourses;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import methodsforhelp.Utils;

/**
 *
 * @author Nasos
 */
public class Course {

    private int id;
    private String title;
    private String stream;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private static ArrayList<Course> coursesList = new ArrayList();
    private ArrayList<Student> studentPerCourse = new ArrayList();
    private ArrayList<Teacher> teacherPerCourse = new ArrayList();
    private ArrayList<Assignments> assignmentsPerCourse = new ArrayList();

    public Course(String courseTitle, String stream, String type, String startDate, String endDate) {
        this.title = courseTitle;
        this.stream = stream;
        this.type = type;
        this.startDate = LocalDate.parse(startDate, Utils.getFormatter());;
        this.endDate = LocalDate.parse(endDate, Utils.getFormatter());;

    }

    public Course(int id, String courseTitle, String stream, String type, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = courseTitle;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentPerCourse(ArrayList<Student> studentPerCourse) {
        this.studentPerCourse = studentPerCourse;
    }

    public Course() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate date) {
        this.endDate = date;
    }

    public static void saveCourse(Course course) throws SQLException {
        TableCourses.createCourse(course);
    }

    public static ArrayList<Course> getCoursesList() {
        return TableCourses.allCourses();
    }

    public ArrayList<Teacher> getTeacherPerCourse() {
        return TableCourses.teacherPerCourse(this);
    }

    public ArrayList<Student> getStudentPerCourse() {
        return TableCourses.studenPerCourse(this);
    }

    public  ArrayList<Assignments> getAssignmentPerCourse() {
        return TableCourses.assignmentPerCourse(this);
    }

    public void addStudent(Student student) {
        TableCourses.addStudentsToCourse(this, student);
    }

    public ArrayList<Student> getUnregisteredStudents() {
        return TableCourses.getUnregisteredStudents(this);
    }

    public void addTeacher(Teacher teacher) {
         TableCourses.addTeacherToCourse(this, teacher);
    }

    public ArrayList<Teacher> getUnregisteredTeachers() {
        return TableCourses.getUnregisteredTeachers(this);
    }

    public void addAssignments(Assignments assignment) {
        TableCourses.addAssignmentToCourse(this, assignment);
    }

    public ArrayList<Assignments> getUnregisteredAssignments() {
        return TableCourses.getUnregisteredAssignments(this);
    }

    /**
     * To get AssignmentPerCourse.
     *
     * @param course
     * @return boolean
     */
    public static boolean existCourse(Course course) {
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getTitle().equals(course.getTitle())
                    && (coursesList.get(i).getStream().equals(course.getStream()))) {
                return true;
            }
        }
        return false;
    }



    public boolean ifStudentExist(Student s) {

        for (Student student : this.getStudentPerCourse()) {
            if (student.equals(s)) {
                return true;
            }
        }

        return false;
    }

    /*Here we the methods below we add Students Teachers Assignments. */
    public void addStudents(ArrayList<Student> syntheticStudents) {
        for (Student student : syntheticStudents) {
            TableCourses.addStudentsToCourse(this, student);
        }

    }

    public void addTeachers(ArrayList<Teacher> syntheticTeachers) {
        for (Teacher teacher : syntheticTeachers) {
            this.teacherPerCourse.add(teacher);
        }
    }

    public void addAssignments(ArrayList<Assignments> syntheticAssignments) {
        for (Assignments assignment : syntheticAssignments) {
            this.assignmentsPerCourse.add(assignment);
        }
    }
    
    public void updateCourseTeacher(){
    TableCourses.updateCourse(this);
    }
    
    public void deleteCourse(){
    TableCourses.deleteCourse(this.id);
    }
    
    public void deleteCourseGrade(){
    TableCourses.deleteCourseGrade(this.id);
    }
    
    public void deleteCourseStudent(){
    TableCourses.deleteCourseStudent(this.id);
    }
    
    public void deleteCourseTeacher(){
    TableCourses.deleteCourseTeacher(this.id);
    }
    
    public void deleteCourseAssignment(){
    TableCourses.deleteCourseAssignment(this.id);
    }
   
    @Override
    public String toString() {
        
        return String.format("  %-5s%-10s%-10s%-13s%-14s%-15s", id ,title ,stream ,type ,startDate, endDate);
    }
}
