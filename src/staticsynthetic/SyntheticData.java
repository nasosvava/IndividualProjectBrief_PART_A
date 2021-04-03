/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticsynthetic;

import mainclasses.Student;
import mainclasses.Course;
import mainclasses.Teacher;
import mainclasses.Assignments;
import java.util.ArrayList;
import java.util.Collections;
import mainclasses.Grades;

/**
 *
 * @author Nasos
 */
public class SyntheticData {

    public static void Data() {
        Student s = new Student("Nasos", "Vavatsikos", "26-08-1993", 2500);
        Student s1 = new Student("Vagelis", "Vasiliou", "30-01-1990", 2500);
        Student s2 = new Student("Labros", "Labrou", "15-03-2000", 2200);
        Student s3 = new Student("Anastasia", "Tsoukala", "31-12-1991", 2500);
        Student s4 = new Student("Xrhstos", "Kotsou", "20-03-1993", 2500);
        Student s5 = new Student("Lena", "Oikonomou", "11-02-1990", 2500);
        Student s6 = new Student("Vaso", "Leka", "02-03-2001", 2200);
        Student s7 = new Student("Maria", "Likoudi", "04-02-1995", 2200);
        Student s8 = new Student("Kwstas", "Tasakos", "05-04-1980", 2500);
        Student s9 = new Student("Giannis", "Kakavas", "22-05-1992", 2200);
        Student s10 = new Student("Giannis", "Arapis", "08-06-1991", 2200);
        Student s11 = new Student("Giannis", "Dimitriou", "30-01-1994", 2500);
        Student s12 = new Student("Kwstas", "Skiniotis", "15-03-2000", 2200);
        Student s13 = new Student("Kwstas", "Marinos", "31-12-1991", 2500);
        Student s14 = new Student("Mairi", "Mposganidi", "20-03-1993", 2500);
        Student s15 = new Student("Zaxos", "Zaxou", "11-02-1990", 2500);
        Student s16 = new Student("Louis", "Lazos", "02-03-2001", 2200);
        Student s17 = new Student("Sergio", "Kanatsi", "04-02-1995", 2200);
        Student s18 = new Student("Kwstas", "Paulopoulos", "05-04-1980", 2500);
        Student s19 = new Student("Giannis", "Panagou", "22-05-1992", 2200);
        Student s20 = new Student("Giannis", "Zwhs", "08-06-1991", 2200);

        Collections.addAll(Student.getStudentsList(), s, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20);

        Teacher t = new Teacher("Nikos", "Tsepetzidis", "Java");
        Teacher t1 = new Teacher("Xrhstos", "Kontoshs", "Java");
        Teacher t2 = new Teacher("Spyros", "Mauros", "Python");
        Teacher t3 = new Teacher("Giorgos", "Hraklidis", "Python");
        Teacher t4 = new Teacher("Zwh", "Tsekoura", "C#");
        Teacher t5 = new Teacher("Manos", "Manou", "C#");
        Teacher t6 = new Teacher("Dimitris", "Goudas", "JavaScript");
        Teacher t7 = new Teacher("Kleio", "Sala", "JavaScript");

        Collections.addAll(Teacher.getTeacherList(), t, t1, t2, t3, t4, t5, t6, t7);

        Course c = new Course("CB13", "Java", "Full Time", "10-01-2021", "10-05-2021");
        Course c1 = new Course("CB13", "Python", "Full Time", "10-01-2021", "10-05-2021");
        Course c2 = new Course("CB13", "JavaScript", "Full Time", "15-02-2021", "10-09-2021");
        Course c3 = new Course("CB13", "C#", "Full Time", "30-12-2020", "10-1-2021");

        Collections.addAll(Course.getCoursesList(), c, c1, c2, c3);

        Assignments a = new Assignments("School", "Create a school", 20, 80, "15-05-2021");
        Assignments a1 = new Assignments("Hospital", "Create a hospital", 20, 80, "15-05-2021");
        Assignments a2 = new Assignments("Pizzaria", "Create a pizzaria", 20, 80, "15-05-2021");
        Assignments a3 = new Assignments("Book Club", "Create a Book Club", 20, 80, "15-05-2021");
        Assignments a4 = new Assignments("Car Sharing", "Create a car sharing web application", 20, 80, "15-05-2021");
        Assignments a5 = new Assignments("Cinema", "Create a Cinema ticket system", 20, 80, "15-05-2021");

        Collections.addAll(Assignments.getAssignmentsList(), a, a1, a2, a3, a4, a5);

        /*Student per course with the help of the method addStudents from course*/
        ArrayList<Student> studentJava = new ArrayList();
        Collections.addAll(studentJava, s1, s2, s3, s4, s5);
        c.addStudents(studentJava);

        ArrayList<Student> studentCsharp = new ArrayList();
        Collections.addAll(studentCsharp, s6, s7, s8, s9, s10, s3);
        c3.addStudents(studentCsharp);

        ArrayList<Student> studentPython = new ArrayList();
        Collections.addAll(studentPython, s11, s12, s13, s14, s15, s7);
        c1.addStudents(studentPython);

        ArrayList<Student> studentJavaScript = new ArrayList();
        Collections.addAll(studentJavaScript, s16, s17, s18, s19, s5);
        c2.addStudents(studentJavaScript);

        //Student assignments grades
        s1.addGrade(new Grades(c, a4, 12, 65));
        s1.addGrade(new Grades(c, a1, 15, 70));
        s2.addGrade(new Grades(c, a4, 18, 80));
        s2.addGrade(new Grades(c, a1, 13, 60));
        s3.addGrade(new Grades(c, a4, 15, 70));
        s3.addGrade(new Grades(c, a1, 12, 55));
        s3.addGrade(new Grades(c, a, 13, 25));
        s4.addGrade(new Grades(c, a4, 5, 45));
        s4.addGrade(new Grades(c, a1, 14, 50));
        s5.addGrade(new Grades(c, a4, 16, 28));
        s5.addGrade(new Grades(c, a1, 19, 78));
        s6.addGrade(new Grades(c3, a, 7, 35));
        s7.addGrade(new Grades(c3, a, 14, 60));
        s8.addGrade(new Grades(c3, a, 13, 30));
        s9.addGrade(new Grades(c3, a, 17, 40));
        s10.addGrade(new Grades(c3, a, 17, 60));
        s11.addGrade(new Grades(c1, a2, 10, 70));
        s11.addGrade(new Grades(c1, a3, 7, 60));
        s12.addGrade(new Grades(c1, a2, 5, 50));
        s12.addGrade(new Grades(c1, a3, 15, 40));
        s13.addGrade(new Grades(c1, a2, 1, 30));
        s13.addGrade(new Grades(c1, a3, 18, 20));
        s14.addGrade(new Grades(c1, a2, 14, 30));
        s14.addGrade(new Grades(c1, a3, 11, 40));
        s15.addGrade(new Grades(c1, a2, 12, 40));
        s15.addGrade(new Grades(c1, a3, 9, 42));
        s7.addGrade(new Grades(c1, a2, 6, 65));
        s7.addGrade(new Grades(c1, a3, 7, 78));
        s16.addGrade(new Grades(c2, a5, 9, 45));
        s18.addGrade(new Grades(c2, a5, 13, 31));
        s19.addGrade(new Grades(c2, a5, 6, 40));
        s20.addGrade(new Grades(c2, a5, 9, 32));
        s5.addGrade(new Grades(c2, a5, 11, 32));
        /*Teacher per course with the help of the method addTeachers from course*/
        ArrayList<Teacher> teacherJava = new ArrayList();
        Collections.addAll(teacherJava, t, t1);
        c.addTeachers(teacherJava);

        ArrayList<Teacher> teacherCsharp = new ArrayList();
        Collections.addAll(teacherCsharp, t3, t2);
        c3.addTeachers(teacherCsharp);

        ArrayList<Teacher> teacherPython = new ArrayList();
        Collections.addAll(teacherPython, t4, t5);
        c1.addTeachers(teacherCsharp);

        ArrayList<Teacher> teacherJavaScript = new ArrayList();
        Collections.addAll(teacherJavaScript, t6, t7);
        c2.addTeachers(teacherCsharp);

        /*Assignments per course with the help of the method addAssignments from course*/
        ArrayList<Assignments> assignJava = new ArrayList();
        Collections.addAll(assignJava, a1, a4);
        c.addAssignments(assignJava);

        ArrayList<Assignments> assignPython = new ArrayList();
        Collections.addAll(assignPython, a3, a2);
        c1.addAssignments(assignPython);

        ArrayList<Assignments> assignJavaScript = new ArrayList();
        Collections.addAll(assignJavaScript, a5);
        c2.addAssignments(assignJavaScript);

        ArrayList<Assignments> assignSharp = new ArrayList();
        Collections.addAll(assignSharp, a);
        c3.addAssignments(assignSharp);

    }
}
