/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

import java.time.LocalDate;
import java.util.ArrayList;
import methodsforhelp.Utils;

/**
 *
 * @author Nasos
 */
public class Course {

    private String courseTitle;
    private String stream;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private static ArrayList<Course> coursesList = new ArrayList();
    private  ArrayList<Student> studentPerCourse = new ArrayList();
    private  ArrayList<Teacher> teacherPerCourse =new ArrayList();
    private  ArrayList<Assignments> assignmentsPerCourse = new ArrayList();

    
    public Course(String courseTitle, String stream, String type, String startDate, String endDate) {
        this.courseTitle = courseTitle;
        this.stream = stream;
        this.type = type;
        this.startDate = LocalDate.parse(startDate,Utils.getFormatter());;
        this.endDate = LocalDate.parse(endDate,Utils.getFormatter());;

    }

    public void setStudentPerCourse(ArrayList<Student> studentPerCourse) {
        this.studentPerCourse = studentPerCourse;
    }

    public Course() {
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
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

    public void setStartDate() {
        this.startDate = Utils.validationLocalDate();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate() {
        this.endDate = Utils.validationLocalDate();
    }

    public static void saveCourse(Course course) {
        Course.coursesList.add(course);
    }

    public static ArrayList<Course> getCoursesList() {
        return coursesList;
    }
    
    public ArrayList<Teacher> getTeacherPerCourse(){
    return teacherPerCourse;
    }

    public ArrayList<Student> getStudentPerCourse() {
        return studentPerCourse;
    }
    
    public ArrayList<Assignments> getAssignmentPerCourse(){
        return assignmentsPerCourse;
    }

    public void addStudent(Student student) {
        this.studentPerCourse.add(student);
    }
    
    public void addTeacher(Teacher teacher){
        this.teacherPerCourse.add(teacher);
    }
    
    public void addAssignments(Assignments assignment){
        this.assignmentsPerCourse.add(assignment);
    }
    /**
     * To get AssignmentPerCourse.
     * @param course
     * @return boolean
     */
    public static boolean existCourse(Course course) {
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getCourseTitle().equals(course.getCourseTitle())
                    && (coursesList.get(i).getStream().equals(course.getStream()))) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return  "Course Title : " + courseTitle + "\nStream : " + stream + "\nType : " + type + "\nStartDate : " + startDate + "\nEndDate : " + endDate ;
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
    public  void addStudents(ArrayList<Student> syntheticStudents){
        for(Student student : syntheticStudents){
           this.studentPerCourse.add(student);
        }
        
    }
    
    public  void addTeachers(ArrayList<Teacher> syntheticTeachers){
        for(Teacher teacher : syntheticTeachers){
            this.teacherPerCourse.add(teacher);
        }
    }
    
    public void addAssignments(ArrayList<Assignments> syntheticAssignments){
        for(Assignments assignment : syntheticAssignments){
            this.assignmentsPerCourse.add(assignment);
        }
    }

}

