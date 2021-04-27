/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.GenericDao.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import mainclasses.Assignments;
import mainclasses.Course;
import methodsforhelp.colour;

/**
 *
 * @author Nasos
 */
public class TableAssignments extends GenericDao {

    private static TableAssignments data;

    private static final String FINDALL = "SELECT * FROM assignments ";
    private static final String INSERT = "INSERT INTO assignments (title, description , sub_date_time , oral_mark , total_mark) values (?, ?, ? ,? ,?)";
    private static final String IFEXIST = "SELECT * FROM assignments WHERE title = ? AND description = ? AND sub_date_time = ?";
    private static final String FINDBYID = "SELECT * FROM assignments WHERE id = ? ";
    private static final String GETCOURSES = "SELECT S.id, S.title , S.stream ,S.type ,S.start_date ,S.end_date FROM courses S INNER JOIN courses_assignments R ON S.id = R.assignments_id INNER JOIN courses C ON R.course_id = C.id  WHERE C.id =  ?";
    private static final String UPDATE = "UPDATE Assignments SET title = ?, description = ? , oral_mark = ? , toral_mark ,sub_date_time = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM assignments where id=?";
    private static final String DELETEASSIGNMENTCOURSE = "DELETE FROM courses_assignments where assignment_id=?";
    private static final String DELETEGRADES = "DELETE FROM grades where grade_id=?";

    private TableAssignments() {
    }

    private static TableAssignments getInstance() {
        if (data == null) {
            data = new TableAssignments();
        }
        return data;
    }

    public static ArrayList<Assignments> allAssignments() {
        ArrayList<Assignments> allAssignments = new ArrayList();
        Connection conn = getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                LocalDate subDateTime = rs.getDate("sub_date_time").toLocalDate();
                Assignments assignment = new Assignments(id, title, description, oralMark, totalMark, subDateTime);
                allAssignments.add(assignment);

            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return allAssignments;
    }

    public static void createAssignment(Assignments assignment) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(INSERT);
            st.setString(1, assignment.getTitle());
            st.setString(2, assignment.getDescription());
            st.setDate(3, Date.valueOf(assignment.getSubDateTime()));
            st.setInt(4, assignment.getOralMark());
            st.setInt(5, assignment.getTotalMark());
            st.execute();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }

    }

    public static boolean ifAssignmenttExist(Assignments assignment) {
        boolean link = true;
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(IFEXIST);
            stmt.setString(1, assignment.getTitle());
            stmt.setString(2, assignment.getDescription());
            stmt.setDate(3, Date.valueOf(assignment.getSubDateTime()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == false) {
                link = false;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return link;
    }

    public static Assignments getAssignmentById(int id) {
        Assignments assignment = null;
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(FINDBYID);

            ResultSet rs;

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int assignmentId = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                LocalDate subDateTime = rs.getDate("sub_date_time").toLocalDate();
                assignment = new Assignments(assignmentId, title, description, oralMark, totalMark, subDateTime);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return assignment;
    }

    public static ArrayList<Course> getCourses(Assignments a) {
        ArrayList<Course> result = new ArrayList();
        Connection conn = getConnection();
        try {
            PreparedStatement rsAssignments;
            ResultSet rsCourse;
            rsAssignments = conn.prepareStatement(GETCOURSES);
            rsAssignments.setInt(1, a.getId());
            rsCourse = rsAssignments.executeQuery();
            while (rsCourse.next()) {
                int id = rsCourse.getInt("id");
                String title = rsCourse.getString("title");
                String stream = rsCourse.getString("stream");
                String type = rsCourse.getString("type");
                LocalDate startDate = rsCourse.getDate("start_date").toLocalDate();
                LocalDate endDate = rsCourse.getDate("end_date").toLocalDate();
                Course course = new Course(id, title, stream, type, startDate, endDate);
                result.add(course);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static void updateAssignment(Assignments assignment) {
        Connection conn = getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(UPDATE);
            st.setString(1, assignment.getTitle());
            st.setString(2, assignment.getDescription());
            st.setDate(3, Date.valueOf(assignment.getSubDateTime()));
            st.setInt(4, assignment.getOralMark());
            st.setInt(5, assignment.getTotalMark());
            st.setInt(6, assignment.getId());
            int result = st.executeUpdate();
            if (result == 1) {
                System.out.println(colour.TEXT_YELLOW + "Assignment succesfully updated!" + colour.TEXT_RESET);
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

    public static void deleteFromCourse(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETEASSIGNMENTCOURSE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    public static void deleteFromGrades(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETEGRADES);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

}
