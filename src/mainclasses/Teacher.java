/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclasses;

import java.util.ArrayList;

/**
 *
 * @author Nasos
 */
public class Teacher {

    private String firstName;
    private String lastName;
    private String teachingCourses;
    private static ArrayList<Teacher> teacherList = new ArrayList();

    public Teacher() {
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

    @Override
    public String toString() {
        return firstName + " " + lastName + "\nTeaching Courses : " + teachingCourses;
    }

    public static void saveTeacher(Teacher newTeacher) {
        Teacher.teacherList.add(newTeacher);
    }

    public static ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }
    /**
     * To get Teacher Per Course.
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
     * @param teacher
     * @return boolean
     */
    public static boolean existTeacher(Teacher teacher) {
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getFirstName().equals(teacher.getFirstName())
                    && (teacherList.get(i).getLastName().equals(teacher.getLastName()))
                    && (teacherList.get(i).getTeachingCourses().equals(teacher.getTeachingCourses()))) {
                return true;
            }
        }
        return false;
    }
}
