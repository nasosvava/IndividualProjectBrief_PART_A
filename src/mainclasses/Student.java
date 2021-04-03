/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import methodsforhelp.Utils;

/**
 *
 * @author Nasos
 */
public class Student {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int tuitionFees;
    private static ArrayList<Student> studentList = new ArrayList();
    private ArrayList<Grades> grade = new ArrayList();
    

    public Student() {

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

    public void setDateOfBirth() {
        this.dateOfBirth = Utils.validationLocalDate();
    }

    public int getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public static void saveStudent(Student newStudent) {
        Student.studentList.add(newStudent);
    }

    public static ArrayList<Student> getStudentsList() { //Epistrefh olous tous ma8htes
        return studentList;
    }

   

    public static void setStudentList(ArrayList<Student> studentList) {
        Student.studentList = studentList;
    }

    public ArrayList<Grades> getGrade() {
        return grade;
    }

    public void setGrades(ArrayList<Grades> grade) {
        this.grade = grade;
    }
    
    public void addGrade(Grades g){
        this.grade.add(g);
    }
    
    /**
     * saves student grades.
     * @param assignment
     * @param course
     * @param oralMark
     * @param totalMark 
     */
    public void saveMark(Assignments assignment , Course course , int oralMark , int totalMark){
        Grades grade = new Grades( course, assignment  , oralMark , totalMark);
        this.grade.add(grade);
    }
    
    /**
     * Checks if we have grades.
     * @param assignment
     * @param course
     * @return 
     */
    public boolean gradeExist(Assignments assignment , Course course){
        boolean exist=false;
      
        for (Grades g : this.grade) {
            if(course.equals(g.getCourse())){
                if(assignment.equals(g.getAssignment())){
                  exist=true;
                }
            }
        }
        return exist;
    }
    
    /**We make a copy arrayList  of Assignment Per Course
     *we check one by one the assignments if they are already mark and we remove them.@e return the assignment that we do not have grade.
     * @param course
     * @return ArrayList
     */
    
    public ArrayList<Assignments> notMarkAssignments(Course course){
        ArrayList<Assignments> assignm = new ArrayList(course.getAssignmentPerCourse());
        Iterator <Assignments> iter = assignm.iterator();
        while(iter.hasNext()){
            Assignments a = iter.next();  //every time this takes the new assignments.
            if(gradeExist(a , course)){
                iter.remove();             // here we remove this assignment that we have grade.
            }
        }
        return assignm;
    }
    
    /**
     * We take student Grades.
     * @param course
     * @param assignment
     * @return Grades
     */
    public Grades getStudentGrade(Course course , Assignments assignment){
        Grades g = new Grades();
        for (Grades grade : this.grade) {
            if(course.equals(grade.getCourse()) &&  assignment.equals(grade.getAssignment())){
                g=grade;
            }
        }
        return g;
    }

    /**
     * We take all the Assignments
     * @return ArrayList
     */
    public ArrayList<Assignments> getAllAssignments(){
       ArrayList<Assignments> result =new ArrayList();
       for(Course course : this.getCourses()){ 
           for(Assignments assignment : course.getAssignmentPerCourse()){  
               result.add(assignment);
           }
       }
       return result;
    }
    


    /**
     * Checks if the student exist in the list so we will not have duplicate Students.
     * @param student
     * @return Boolean
     */
    public static boolean existStudent(Student student) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getFirstName().equals(student.getFirstName())
                    && (studentList.get(i).getLastName().equals(student.getLastName()))
                    && (studentList.get(i).getDateOfBirth().equals(student.getDateOfBirth()))) {
                return true;

            }

        }
        return false;
    }

    /**
     * We take Student per Course.
     * @return ArrayList
     */
    
    public ArrayList<Course> getCourses() {
        ArrayList<Course> result = new ArrayList();
        for (Course course : Course.getCoursesList()) {

            if (course.getStudentPerCourse().contains(this)) { //
                result.add(course);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\nDateOfBirth : " + dateOfBirth + "\nTuitionFees : " + tuitionFees ;
    }
    
    

}