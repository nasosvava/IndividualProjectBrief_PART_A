/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

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
import mainclasses.Grades;
import mainclasses.Student;
import methodsforhelp.colour;

/**
 *
 * @author Nasos
 */
public class TableStudents extends GenericDao {

    static TableStudents data;

    private static final String INSERT = "INSERT INTO students (first_name, last_name, date_of_birth,tuition_fees) values (?, ?, ? ,? )";
    private static final String IFEXIST = "SELECT * FROM students WHERE first_name = ? AND last_name = ? AND date_of_birth = ?";
    private static final String INSERTGRADE = "INSERT INTO grades (students_id, assignments_id, courses_id,oral_mark , total_mark) values (?, ?, ? ,?,? )";
    private static final String IFGRADEEXIST = "SELECT * FROM grades WHERE students_id = ? AND assignments_id = ? AND courses_id = ? ";
    private static final String FINDUNMARKEDASSIGNMENTS = "SELECT * FROM assignments A INNER JOIN courses_assignments R ON A.id =R.assignments_id INNER JOIN courses C ON R.course_id = C.id INNER JOIN courses_students X ON C.id = X.course_id INNER JOIN students S ON X.student_id = S.id WHERE S.id = ? AND C.id =? AND A.id NOT IN (SELECT G.assignments_id FROM  grades G WHERE G.students_id=? AND G.courses_id = ?)";
    private static final String FINDMARKEDASSIGNMENTS = "SELECT * FROM assignments S  WHERE S.id IN (SELECT assignment_id  FROM  grades C WHERE C.students_id = ? AND C.courses_id = ?)";
    private static final String FINDGRADE = "SELECT * FROM grades S WHERE S.students_id = ? AND S.courses_id = ? AND S.assignments_id = ? ";
    private static final String FINDBYID = "SELECT * FROM students WHERE id = ? ";
    private static final String FINDASSIGNMENTSTUDENT = "SELECT * FROM assignments A INNER JOIN courses_assignments R ON A.id =R.assignments_id INNER JOIN courses C ON R.course_id = C.id INNER JOIN courses_students X ON C.id = X.course_id INNER JOIN students S ON X.student_id = S.id WHERE S.id = ?";
    private static final String UPDATE = "UPDATE Students SET first_name = ?, last_name= ? , date_of_birth = ? ,tuition_fees=? WHERE id = ?";
    private static final String DELETE = "DELETE FROM students WHERE id = ?";
    private static final String DELETEGRADE = "DELETE FROM grades WHERE students_id = ?";
    private static final String DELETESTUDENTFROMCOURSE = "DELETE FROM courses_students WHERE student_id = ?";
    private static final String DELETEGRADEFROMSTUDENT = "DELETE FROM grades WHERE students_id = ? AND courses_id = ? AND assignments_id = ?";

    public static void deleteStudentCourse(int id) {
        Connection conn = GenericDao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETESTUDENTFROMCOURSE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    private TableStudents() {
    }

    private static TableStudents getInstance() {
        if (data == null) {
            data = new TableStudents();
        }
        return data;
    }

    public static ArrayList<Student> allStudent() {
        ArrayList<Student> allStudents = new ArrayList();
        Connection conn = getConnection();
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM students ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
                int tutionFees = rs.getInt("tuition_fees");
                Student student = new Student(id, fName, lName, dateOfBirth, tutionFees);
                allStudents.add(student);

            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return allStudents;
    }

    public static void createStudent(Student student) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(INSERT);
            st.setString(1, student.getFirstName());
            st.setString(2, student.getLastName());
            st.setDate(3, Date.valueOf(student.getDateOfBirth()));
            st.setInt(4, student.getTuitionFees());
            st.execute();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }

    }

    public static boolean ifStudentExist(Student student) {
        boolean link = true;
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(IFEXIST);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == false) {
                link = false;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return link;
    }

    public static void addGrade(Student student, Grades grade) {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(INSERTGRADE);
            st.setInt(1, student.getId());
            st.setInt(2, grade.getAssignment().getId());
            st.setInt(3, grade.getCourse().getId());
            st.setInt(4, grade.getOralMark());
            st.setInt(5, grade.getTotalMark());
            st.execute();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
    }

    public static boolean studentGradeExist(Student s, Course c, Assignments a) {
        boolean exist = true;
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(IFGRADEEXIST);
            st.setInt(1, s.getId());
            st.setInt(2, a.getId());
            st.setInt(3, c.getId());

            ResultSet rs = st.executeQuery();
            if (rs.next() == false) {
                exist = false;
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return exist;
    }

    public static ArrayList<Assignments> unMarkedAssignments(Student s, Course c) {
        ArrayList<Assignments> result = new ArrayList();
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(FINDUNMARKEDASSIGNMENTS);
            st.setInt(1, s.getId());
            st.setInt(2, c.getId());
            st.setInt(3, s.getId());
            st.setInt(4, c.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                LocalDate subDateTime = rs.getDate("sub_date_time").toLocalDate();
                Assignments assignment = new Assignments(id, title, description, oralMark, totalMark, subDateTime);
                result.add(assignment);

            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return result;
    }

    public static ArrayList markedAssignments(Student s, Course c) {
        ArrayList<Assignments> result = new ArrayList();
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(FINDMARKEDASSIGNMENTS);
            st.setInt(1, s.getId());
            st.setInt(2, c.getId());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                LocalDate subDateTime = rs.getDate("sub_date_time").toLocalDate();
                Assignments assignment = new Assignments(id, title, description, oralMark, totalMark, subDateTime);
                result.add(assignment);

            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return result;

    }

    public static Grades getGrade(Student s, Course c, Assignments a) {
        Grades grade = null;
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(FINDGRADE);
            st.setInt(1, s.getId());
            st.setInt(2, c.getId());
            st.setInt(3, a.getId());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                int sID = rs.getInt("students_id");
                int cID = rs.getInt("courses_id");
                int aID = rs.getInt("assignments_id");
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                grade = new Grades(getStudentById(sID), TableCourses.getCourseById(cID), TableAssignments.getAssignmentById(aID), oralMark, totalMark);
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return grade;

    }

    public static Student getStudentById(int id) {
        Student student = null;
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(FINDBYID);
            ResultSet rs;
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
                int tutionFees = rs.getInt("tuition_fees");
                student = new Student(studentId, fName, lName, dateOfBirth, tutionFees);

            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return student;
    }

    public static ArrayList<Assignments> assignmentsPerStudent(Student student) {
        ArrayList<Assignments> allAssignments = new ArrayList();
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(FINDASSIGNMENTSTUDENT);
            ResultSet rs;
            stmt.setInt(1, student.getId());
            rs = stmt.executeQuery();
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

    public static void updateStudent(Student student) {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            stmt.setInt(4, student.getTuitionFees());
            stmt.setInt(5, student.getId());
            int result = stmt.executeUpdate();
            if (result == 1) {
                System.out.println(colour.TEXT_YELLOW + "Student succesfully updated!" + colour.TEXT_RESET);
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

    public static void deleteGrade(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETEGRADE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }
    
        public static void deleteGrade(int student_id ,int course_id ,int assigment_id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETEGRADE);
            pstm.setInt(1, student_id);
            pstm.setInt(2, course_id);
            pstm.setInt(3, assigment_id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

}
