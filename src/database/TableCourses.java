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
import java.util.Scanner;
import mainclasses.Assignments;
import mainclasses.Course;
import mainclasses.Student;
import mainclasses.Teacher;
import methodsforhelp.colour;

/**
 *
 * @author Nasos
 */
public class TableCourses extends GenericDao {

    private static final String INSERT = "INSERT INTO courses (title, stream, type,start_date ,end_date) values (?, ?, ? ,? ,?)";
    private static final String INSERTSTUDENTSINTOCOURSES = "INSERT INTO courses_students (course_id,student_id) VALUES (?,?)";
    private static final String INSERTTEACHERTOCOURSE = "INSERT INTO courses_teachers (course_id,teacher_id) VALUES (?,?)";
    private static final String INSERASSIGNMENTINTOCOURSE = "INSERT INTO courses_assignments (course_id,assignments_id) VALUES (?,?)";

    private static final String FINDSTUDENTPERCOURSE = "SELECT S.id, S.first_name, S.last_name, S.date_of_birth, S.tuition_fees FROM students S INNER JOIN courses_students R ON S.id = R.student_id INNER JOIN courses C ON R.course_id = C.id  WHERE C.id =  ?";
    private static final String FINDCOURSESPERSTUDENT = "SELECT * FROM courses C INNER JOIN courses_students R ON C.id = R.course_id INNER JOIN students S ON R.student_id = S.id  WHERE S.id =  ?";
    private static final String FINDSTUDENTSWITHMORECOURSES = "SELECT S.id ,S.first_name ,S.last_name ,S.date_of_birth,S.tuition_fees\n" + "FROM students S\n" + "INNER JOIN courses_students R ON S.id = R.student_id\n" + "GROUP BY S.id\n" + "HAVING COUNT(*) >= 2";
    private static final String FINDASSIGNMENTPERCOURSE = "SELECT S.id, S.title , S.description ,S.sub_date_time ,S.oral_mark ,S.total_mark FROM assignments S INNER JOIN courses_assignments R ON S.id = R.assignments_id INNER JOIN courses C ON R.course_id = C.id  WHERE C.id =  ?";
    private static final String FINDALL = "SELECT * FROM courses ";
    private static final String FINDSTUDENTWITHOUTCOURSE = "select * from students where id not in  select student_id  from courses_students s inner join courses c on s.course_id  = c.id  where c.id = ? ";
    private static final String FINDTEACHERSWIHTOUTCOURSE = "select * from teachers where id not in select teacher_id from courses_teachers s inner join courses c on s.course_id  = c.id where c.id = ? ";
    private static final String FINDASSIGNMENTWIHTOUTCOURSE = "select * from assignments where id not in select assignments_id from courses_assignments s inner join courses c on s.course_id  = c.id where c.id = ? ";
    private static final String FINDBYID = "SELECT * FROM courses WHERE id = ? ";
    private static final String FINDTEACHERSPERCOURSE = "SELECT S.id, S.first_name, S.last_name, S.subject FROM teachers S INNER JOIN courses_teachers R ON S.id = R.teacher_id INNER JOIN courses C ON R.course_id = C.id  WHERE C.id =  ?";

    private static final String UPDATE = "UPDATE Courses SET title = ?, stream = ? , type = ? , start_date ,end_date = ? WHERE id = ?";
    private static final String IFEXIST = "SELECT * FROM courses WHERE title = ? AND stream = ? AND type = ? AND start_date = ? AND end_date = ?";

    private static final String DELETE = "DELETE FROM courses where id=?";
    private static final String DELETEGRADE = "DELETE FROM grades where courses_id=?";
    private static final String DELETECOURSEFROMASSIGNMENT = "DELETE FROM courses_assignments where course_id=?";
    private static final String DELETECOURSESTUDENT = "DELETE FROM courses_students WHERE course_id=?";
    private static final String DELETECOURSETEACHER = "DELETE FROM courses_teachers WHERE course_id=?";

    private static final String DELETEFROMCOURSESTEACHERS = "DELETE FROM courses_teachers WHERE course_id=? AND teacher_id=?";
    private static final String DELETEFROMCOURSESTUDENT = "DELETE FROM courses_students WHERE course_id=? AND student_id=?";
    private static final String DELETEFROMCOURSEASSIGNMENT = "DELETE FROM courses_assignments WHERE course_id=? AND assignment_id=?";

    private static Scanner scanner = new Scanner(System.in);
    private static TableCourses data;
    private static ArrayList<Course> course = new ArrayList();

    public static void addStudentsToCourse(Course course, Student student) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(INSERTSTUDENTSINTOCOURSES);
            pstmt.setInt(1, course.getId());
            pstmt.setInt(2, student.getId());
            pstmt.execute();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Student> studenPerCourse(Course c) {
        ArrayList<Student> result = new ArrayList();
        Connection conn = getConnection();
        try {
            ResultSet rsStudent;
            PreparedStatement rsCourse;
            rsCourse = conn.prepareStatement(FINDSTUDENTPERCOURSE);
            rsCourse.setInt(1, c.getId());
            rsStudent = rsCourse.executeQuery();
            while (rsStudent.next()) {
                int id = rsStudent.getInt("id");
                String firstName = rsStudent.getString("first_name");
                String lastName = rsStudent.getString("last_name");
                LocalDate dateOfB = rsStudent.getDate("date_of_birth").toLocalDate();
                int tution = rsStudent.getInt("tuition_fees");
                Student s = new Student(id, firstName, lastName, dateOfB, tution);
                result.add(s);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static ArrayList<Course> coursesPerStudent(Student s) {
        ArrayList<Course> result = new ArrayList();
        Connection conn = getConnection();
        try {
            ResultSet rs;
            PreparedStatement rsCourse;
            rsCourse = conn.prepareStatement(FINDCOURSESPERSTUDENT);
            rsCourse.setInt(1, s.getId());
            rs = rsCourse.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String stream = rs.getString("stream");
                String type = rs.getString("type");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();
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

    public static ArrayList<Student> studentWithinMoreThanOneCourse() {
        ArrayList<Student> result = new ArrayList();
        Connection conn = getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(FINDSTUDENTSWITHMORECOURSES);
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
                int tutionFees = rs.getInt("tuition_fees");
                Student student = new Student(id, fName, lName, dateOfBirth, tutionFees);
                result.add(student);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static ArrayList<Assignments> assignmentPerCourse(Course c) {
        ArrayList<Assignments> result = new ArrayList();
        Connection conn = getConnection();
        try {
            ResultSet rsAssignments;
            PreparedStatement rsCourse;
            rsCourse = conn.prepareStatement(FINDASSIGNMENTPERCOURSE);
            rsCourse.setInt(1, c.getId());
            rsAssignments = rsCourse.executeQuery();
            while (rsAssignments.next()) {
                int id = rsAssignments.getInt("id");
                String title = rsAssignments.getString("title");
                String description = rsAssignments.getString("description");
                LocalDate sub_date_time = rsAssignments.getDate("sub_date_time").toLocalDate();
                int oralMark = rsAssignments.getInt("oral_mark");
                int totalMark = rsAssignments.getInt("total_mark");
                Assignments assignment = new Assignments(id, title, description, oralMark, totalMark, sub_date_time);
                result.add(assignment);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static void addAssignmentToCourse(Course course, Assignments assignment) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(INSERASSIGNMENTINTOCOURSE);
            pstmt.setInt(1, course.getId());
            pstmt.setInt(2, assignment.getId());
            pstmt.execute();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    private static TableCourses getInstance() {
        if (data == null) {
            data = new TableCourses();
        }
        return data;
    }

    public static ArrayList<Course> allCourses() {
        ArrayList<Course> allCourses = new ArrayList();
        Connection conn = getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(FINDALL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String stream = rs.getString("stream");
                String type = rs.getString("type");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();

                Course course = new Course(id, title, stream, type, startDate, endDate);
                allCourses.add(course);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return allCourses;
    }

    public static void createCourse(Course course) throws SQLException {
        Connection conn = getConnection();
        try {
            PreparedStatement st = conn.prepareStatement(INSERT);
            st.setString(1, course.getTitle());
            st.setString(2, course.getStream());
            st.setString(3, course.getType());
            st.setDate(4, Date.valueOf(course.getStartDate()));
            st.setDate(5, Date.valueOf(course.getEndDate()));

            st.execute();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }

    }

    public static boolean ifCourseExist(Course course) {
        boolean link = true;
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(IFEXIST);
            stmt.setString(1, course.getTitle());
            stmt.setString(2, course.getStream());
            stmt.setString(3, course.getType());
            stmt.setDate(4, Date.valueOf(course.getStartDate()));
            stmt.setDate(5, Date.valueOf(course.getEndDate()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == false) {
                link = false;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return link;
    }

    public static ArrayList<Student> getUnregisteredStudents(Course course) {
        ArrayList<Student> result = new ArrayList();
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(FINDSTUDENTWITHOUTCOURSE);
            stmt.setInt(1, course.getId());
            ResultSet rs;
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
                int tutionFees = rs.getInt("tuition_fees");
                Student student = new Student(id, fName, lName, dateOfBirth, tutionFees);
                result.add(student);

            }
        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return result;
    }

    public static ArrayList<Teacher> getUnregisteredTeachers(Course course) {
        ArrayList<Teacher> result = new ArrayList();
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(FINDTEACHERSWIHTOUTCOURSE);
            stmt.setInt(1, course.getId());
            ResultSet rs;
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String teachingCourses = rs.getString("subject");
                Teacher teacher = new Teacher(id, fName, lName, teachingCourses);
                result.add(teacher);

            }
        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return result;
    }

    public static ArrayList<Assignments> getUnregisteredAssignments(Course course) {
        ArrayList<Assignments> result = new ArrayList();
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(FINDASSIGNMENTWIHTOUTCOURSE);
            stmt.setInt(1, course.getId());
            ResultSet rs;
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDate sub_date_time = rs.getDate("sub_date_time").toLocalDate();
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                Assignments assignment = new Assignments(id, title, description, oralMark, totalMark, sub_date_time);
                result.add(assignment);

            }
        } catch (SQLException e) {
            System.out.println("Something went wrond: " + e.getMessage());
        }
        return result;
    }

    public static Course getCourseById(int id) {
        Course course = null;
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(FINDBYID);
            ResultSet rs;
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("id");
                String title = rs.getString("title");
                String stream = rs.getString("stream");
                String type = rs.getString("type");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();

                course = new Course(courseId, title, stream, type, startDate, endDate);

            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return course;
    }

    public static void addTeacherToCourse(Course course, Teacher teacher) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(INSERTTEACHERTOCOURSE);
            pstmt.setInt(1, course.getId());
            pstmt.setInt(2, teacher.getId());
            pstmt.execute();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Teacher> teacherPerCourse(Course c) {
        ArrayList<Teacher> result = new ArrayList();
        Connection conn = getConnection();
        try {
            ResultSet rsTeacher;
            PreparedStatement rsCourse;
            rsCourse = conn.prepareStatement(FINDTEACHERSPERCOURSE);
            rsCourse.setInt(1, c.getId());
            rsTeacher = rsCourse.executeQuery();
            while (rsTeacher.next()) {
                int id = rsTeacher.getInt("id");
                String firstName = rsTeacher.getString("first_name");
                String lastName = rsTeacher.getString("last_name");
                String teachingCourse = rsTeacher.getString("subject");
                Teacher t = new Teacher(id, firstName, lastName, teachingCourse);
                result.add(t);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public static void updateCourse(Course course) {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, course.getTitle());
            stmt.setString(2, course.getStream());
            stmt.setString(3, course.getType());
            stmt.setDate(4, Date.valueOf(course.getStartDate()));
            stmt.setDate(5, Date.valueOf(course.getEndDate()));
            stmt.setInt(6, course.getId());
            int result = stmt.executeUpdate();
            if (result == 1) {
                System.out.println(colour.TEXT_YELLOW + "Student succesfully updated!" + colour.TEXT_RESET);
            }
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }

    }

    public static void deleteCourse(int id) {
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

    public static void deleteCourseStudent(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETECOURSESTUDENT);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    public static void deleteCourseTeacher(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETECOURSETEACHER);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    public static void deleteCourseAssignment(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETECOURSEFROMASSIGNMENT);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    public static void deleteCourseGrade(int id) {
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

    public static void deleteCourseStudent(int course_id, int student_id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETEFROMCOURSESTUDENT);
            pstm.setInt(1, course_id);
            pstm.setInt(2, student_id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    public static void deleteCourseTeacher(int course_id, int teacher_id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETEFROMCOURSESTEACHERS);
            pstm.setInt(1, course_id);
            pstm.setInt(2, teacher_id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }
    
        public static void deleteCourseAssignment(int course_id , int assingment_id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETECOURSEFROMASSIGNMENT);
            pstm.setInt(1, course_id);
            pstm.setInt(2, assingment_id);
            int result = pstm.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }
}
