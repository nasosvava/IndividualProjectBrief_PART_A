/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;
import methodsforhelp.Utils;

/**
 *
 * @author Nasos
 */
public class Assignments {

    private String title;
    private String description;
    private int oralMark;
    private int totalMark;
    private LocalDate subDateTime;

    private static ArrayList<Assignments> assignmentsList = new ArrayList();

    public Assignments(String title, String description, int oralMark, int totalMark, String subDateTime) {
        this.title = title;
        this.description = description;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.subDateTime = LocalDate.parse(subDateTime, Utils.getFormatter());
    }

    public Assignments() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDate date) {
        this.subDateTime = date;
    }

    @Override
    public String toString() {
        return "Title :" + title + "\nDescription : " + description + "\nOralMark : " + oralMark + "\nTotalMark : " + totalMark + "\nSubDateTime : " + subDateTime;
    }

    public static void saveAssignment(Assignments assignment) {
        assignmentsList.add(assignment);
    }

    public static ArrayList<Assignments> getAssignmentsList() {
        return assignmentsList;
    }

    /**
     * To get AssignmentPerCourse.
     * @return ArrayList
     */
    public ArrayList<Course> getCourses() {
        ArrayList<Course> result = new ArrayList();
        for (Course course : Course.getCoursesList()) {

            if (course.getAssignmentPerCourse().contains(this)) {
                result.add(course);
            }
        }
        return result;
    }

    /**
     * Checks if the student exist in the list so we will not have duplicate Assignments.
     * @param student
     * @return Boolean
     */
    public static boolean existAssignemnt(Assignments assignment) {
        for (int i = 0; i < assignmentsList.size(); i++) {
            if (assignmentsList.get(i).getTitle().equals(assignment.getTitle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns number of the week of the year for assignment
     * @return  integer
     */
    public int getWeek() {
        TemporalField week = WeekFields.ISO.weekOfYear();
        int num = this.getSubDateTime().get(week);
        return num;
    }
}
