/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

import database.TableCourses;
import database.TableTeachers;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nasos
 */
public class Teacher {

    private int id;
    private String firstName;
    private String lastName;
    private String teachingCourses;
    private static ArrayList<Teacher> teacherList = new ArrayList();

    public Teacher() {
    }

    public Teacher(int id, String firstName, String lastName, String teachingCourses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teachingCourses = teachingCourses;
    }

    public int getId() {
        return id;
    }

    public Teacher(String firstName, String lastName, String teachingCourses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teachingCourses = teachingCourses;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTeachingCourses() {
        return teachingCourses;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTeachingCourses(String teachingCourses) {
        this.teachingCourses = teachingCourses;
    }

    public static void saveTeacher(Teacher newTeacher) throws SQLException {
        TableTeachers.createTeacher(newTeacher);
    }

    public static ArrayList<Teacher> getTeacherList() {
        return TableTeachers.allTeacher();
    }

    /**
     * To get Teacher Per Course.
     *
     * @return ArrayList
     */
    public ArrayList<Course> getCourses() {
        ArrayList<Course> result = new ArrayList();
        for (Course course : Course.getCoursesList()) {

            if (course.getTeacherPerCourse().contains(this)) {
                result.add(course);
            }
        }
        return result;
    }

    /**
     * Check if the current object exist in the list.
     *
     * @param teacher
     * @return boolean
     */
    public static boolean existTeacher(Teacher teacher) {
        return TableTeachers.ifTeacherExist(teacher);
    }

    public void updateTeacher() {
        TableTeachers.updateTeacher(this);
    }

    public void deleteTeacher() {
        TableTeachers.delete(this.id);
    }

    public void deleteCourseTeacher() {
        TableTeachers.deleteTeacherCourse(this.id);
    }

    @Override
    public String toString() {
        return String.format(" %-5s%-15s%-15s%-15s", id, firstName, lastName, teachingCourses);
    }

}
