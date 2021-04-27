/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selection;

import mainclasses.Course;
import java.util.ArrayList;
import java.util.Scanner;
import methodsforhelp.Utils;
import methodsforhelp.colour;


/**
 *
 * @author Nasos
 */
public class SelectCourses {

    private static Scanner scanner = new Scanner(System.in);
     static ArrayList<Course> selectedCourse = new ArrayList();
    private static ArrayList<Course> copyListCourse = new ArrayList(Course.getCoursesList());

    /**
     * Select many Courses.
     * @return ArrayList with selected Courses
     */
    public static ArrayList<Course> selectManyCourse() {
        boolean quit = false;
        while (quit == false) {
            if (copyListCourse.isEmpty()) {
                System.out.println(colour.TEXT_RED+"You dont have more Course to add!"+colour.TEXT_RESET);
                System.out.println("Make your new Choice!");
                quit = true;
            } else {

                int counter = 1;

                System.out.println(colour.TEXT_YELLOW+"Select a Course!"+colour.TEXT_RESET);

                for (Course course : copyListCourse) {
                    System.out.println(counter + " " + course.getTitle() + " " + course.getStream());
                    counter++;
                }
                int choice = Utils.checkingIntegers(1, copyListCourse.size());

                selectedCourse.add(copyListCourse.get(choice - 1));
                
                break;
            }

        }
        return selectedCourse;
    }
    
        /**
     * Select one Courses.
     * @return Course
     */
    public static Course selectOneCourse(ArrayList<Course> courseList){
        System.out.println(colour.TEXT_YELLOW+"Choose one course!"+colour.TEXT_RESET);
        int counter =1;
        for(Course course : courseList){
            System.out.println(counter + " " + course.getTitle()+ " " + course.getStream()+ " " + course.getStartDate());
            counter++;
        }
        int i=Utils.checkingIntegers(1, courseList.size());
        Course course=courseList.get(i-1);
    return course;
    }

}
