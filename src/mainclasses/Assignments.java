/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

import database.TableAssignments;
import java.sql.SQLException;
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

    private int id;
    private String title;
    private String description;
    private int oralMark;
    private int totalMark;
    private LocalDate subDateTime;

    private static ArrayList<Assignments> assignmentsList = new ArrayList();

    public Assignments(int id, String title, String description, int oralMark, int totalMark, LocalDate subDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.subDateTime = subDateTime;
    }

    public int getId() {
        return id;
    }

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

    public static void saveAssignment(Assignments assignment) throws SQLException {
        TableAssignments.createAssignment(assignment);
    }

    public static ArrayList<Assignments> getAssignmentsList() {
        return TableAssignments.allAssignments();
    }

    /**
     * To get AssignmentPerCourse.
     *
     * @return ArrayList
     */
    public ArrayList<Course> getCourses() {
        return TableAssignments.getCourses(this);
    }

    /**
     * Checks if the student exist in the list so we will not have duplicate
     * Assignments.
     *
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
     *
     * @return integer
     */
    public int getWeek() {
        TemporalField week = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int num = this.getSubDateTime().get(week);
        return num;
    }

    public void updateAssignment() {
        TableAssignments.updateAssignment(this);
    }

    public void deleteAssignment() {
        TableAssignments.delete(this.id);
    }

    public void deleteAssignmentCourse() {
        TableAssignments.deleteFromCourse(this.id);
    }

    public void deleteAssignmentGrades() {
        TableAssignments.deleteFromGrades(this.id);
    }

    @Override
    public String toString() {
        return String.format(" %-5s%-15s%-20s%-23s%-16s%-20s", id, title, description, subDateTime, oralMark, totalMark);
    }
}
