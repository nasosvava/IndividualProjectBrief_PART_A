/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

/**
 *
 * @author Nasos
 */
public class Grades {

    private Course course;
    private Assignments assignment;
    private int oralMark;
    private int totalMark;

    public Grades() {
    }
    
    

    public Grades(Course course, Assignments assignment, int oralMark, int totalMark) {
        this.course = course;
        this.assignment = assignment;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Assignments getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignments assignment) {
        this.assignment = assignment;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        return "Grades{" + "course=" + course + ", assignment=" + assignment + ", oralMark=" + oralMark + ", totalMark=" + totalMark + '}';
    }
    
    
}
