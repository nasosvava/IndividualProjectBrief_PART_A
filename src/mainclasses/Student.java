/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

import database.TableCourses;
import database.TableStudents;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import methodsforhelp.Utils;

/**
 *
 * @author Nasos
 */
public class Student {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int id;
    private int tuitionFees;
    private static ArrayList<Student> studentList = new ArrayList();
    private ArrayList<Grades> grade = new ArrayList();

    public Student() {

    }

    public Student(int id, String firstName, String lastName, LocalDate dateOfBirth, int tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
        this.tuitionFees = tuitionFees;
    }

    public int getId() {
        return id;
    }

    public Student(String firstName, String lastName, String dateOfBirth, int tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, Utils.getFormatter());
        this.tuitionFees = tuitionFees;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate date) {
        this.dateOfBirth = date;
    }

    public int getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public static void saveStudent(Student newStudent) throws SQLException {
        TableStudents.createStudent(newStudent);
    }

    public static ArrayList<Student> getStudentsList() { //Epistrefh olous tous ma8htes
        return TableStudents.allStudent();
    }

    public static void setStudentList(ArrayList<Student> studentList) {
        Student.studentList = studentList;
    }

    public ArrayList<Assignments> getAssignments() {
        return TableStudents.assignmentsPerStudent(this);
    }

    public ArrayList<Grades> getGrade() {
        return grade;
    }

    public void setGrades(ArrayList<Grades> grade) {
        this.grade = grade;
    }

    public void addGrade(Grades g) {
        TableStudents.addGrade(this, g);
    }

    public void updateStudent() {
        TableStudents.updateStudent(this);
    }

    /**
     * saves student grades.
     *
     * @param assignment
     * @param course
     * @param oralMark
     * @param totalMark
     */
    public void saveMark(Assignments assignment, Course course, int oralMark, int totalMark) {
        Grades grade = new Grades(course, assignment, oralMark, totalMark);
        TableStudents.addGrade(this, grade);
    }

    /**
     * Checks if we have grades.
     *
     * @param assignment
     * @param course
     * @return
     */
    public boolean gradeExist(Assignments assignment, Course course) {
        return TableStudents.studentGradeExist(this, course, assignment);
    }

    /**
     * We make a copy arrayList of Assignment Per Course we check one by one the
     * assignments if they are already mark and we remove them.@e return the
     * assignment that we do not have grade.
     *
     * @param course
     * @return ArrayList
     */
    public ArrayList<Assignments> notMarkAssignments(Course course) {
        return TableStudents.unMarkedAssignments(this, course);
    }

    /**
     * We take student Grades.
     *
     * @param course
     * @param assignment
     * @return Grades
     */
    public Grades getStudentGrade(Course course, Assignments assignment) {
        return TableStudents.getGrade(this, course, assignment);
    }

    /**
     * Checks if the student exist in the list so we will not have duplicate
     * Students.
     *
     * @param student
     * @return Boolean
     */
    public static boolean existStudent(Student student) {

        return TableStudents.ifStudentExist(student);
    }

    /**
     * We take Student per Course.
     *
     * @return ArrayList
     */
    public ArrayList<Course> getCourses() {
        return TableCourses.coursesPerStudent(this);
    }

    public static ArrayList<Student> studentWithMoreCourses() {
        return TableCourses.studentWithinMoreThanOneCourse();
    }
    
    public void deleteStudent(){
    TableStudents.delete(this.id);
    }
    
    public void deleteStudentGrade(){
    TableStudents.deleteGrade(this.id);
    }
    
    public void deleteStudentCourse(){
    TableStudents.deleteStudentCourse(this.id);
    }

    @Override
    public String toString() {
        return String.format("  %-7s%-15s%-15s%-15s%-15s", id ,firstName ,lastName ,dateOfBirth ,tuitionFees);
    }

}
