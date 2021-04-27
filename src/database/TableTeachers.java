/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mainclasses.Teacher;
import methodsforhelp.colour;

/**
 *
 * @author Nasos
 */
public class TableTeachers extends GenericDao {

    private static final String FINDALL = "SELECT * FROM teachers ";
    private static final String INSERT = "INSERT INTO teachers (first_name, last_name, subject) values (?, ?, ? )";
    private static final String IFEXIST = "SELECT * FROM teachers WHERE first_name = ? AND last_name = ? AND subject = ?";
    private static final String UPDATE = "UPDATE Teachers SET first_name = ?, last_name= ? , subject WHERE id = ?";
    private static final String DELETE = "DELETE FROM Teachers WHERE id = ?";
    private static final String DELETETEACHERFROMCOURSE = "DELETE FROM courses_teachers WHERE teacher_id = ?";
    static TableTeachers data;

    public static void deleteTeacherCourse(int id) {
        Connection conn = GenericDao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETETEACHERFROMCOURSE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    private TableTeachers() {
    }

    private static TableTeachers getInstance() {
        if (data == null) {
            data = new TableTeachers();
        }
        return data;
    }

    public static ArrayList<Teacher> allTeacher() {
        ArrayList<Teacher> allTeachers = new ArrayList();
        Connection conn = getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String teachingCourse = rs.getString("subject");
                Teacher teacher = new Teacher(id, fName, lName, teachingCourse);
                allTeachers.add(teacher);

            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return allTeachers;
    }

    public static void createTeacher(Teacher teacher) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(INSERT);
            st.setString(1, teacher.getFirstName());
            st.setString(2, teacher.getLastName());
            st.setString(3, teacher.getTeachingCourses());
            st.execute();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }

    }

    public static boolean ifTeacherExist(Teacher teacher) {
        boolean link = true;
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(IFEXIST);
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getTeachingCourses());
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == false) {
                link = false;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return link;
    }

    public static void updateTeacher(Teacher teacher) {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getTeachingCourses());
            stmt.setInt(4, teacher.getId());
            int result = stmt.executeUpdate();
            if (result == 1) {
                System.out.println(colour.TEXT_YELLOW + "Teacher succesfully updated!" + colour.TEXT_RESET);
            }
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }

    }

    public static void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }
}
