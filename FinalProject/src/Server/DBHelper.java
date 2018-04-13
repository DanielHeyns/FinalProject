package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Objects.*;

public class DBHelper {
	private Connection jdbc_connection;
	private Statement statement;
	private String databaseName = "SchoolDB";

	private String connectioninfo = "jdbc:mysql://localhost:3306/" + databaseName, login = "root", password = "simple";

	public DBHelper() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectioninfo, login, password);
			System.out.println("Successful Connection to:" + connectioninfo);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public User checkLogin(int id, String pass) {
		String sql = "SELECT * FROM usertable WHERE ID = " + id;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			if (data.next()) { // User(int i,String p, String e, String f, String l)
				User p = new User(id, data.getString("password"), data.getString("email"), data.getString("firstName"),
						data.getString("lastName"), data.getString("type").charAt(0));
				if (!p.samePass(pass)) {
					return null;
				}
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Course[] profCourses(User p) {
		String sql = "SELECT * FROM coursetable WHERE profID = " + p.getId();
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			int i = 0;
			Course c[] = new Course[5];
			while (data.next()) { // (int i, String cn, int pi, String pn, boolean a, ArrayList<Integer> arr)
				c[i++] = new Course(data.getInt("id"), data.getString("name"), p.getId(), p.getFirstName(),
						data.getBoolean("active"), null);
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Course[] studCourses(User p) {
		String sql = "SELECT * FROM studentenrollmenttable WHERE studentID = " + p.getId();
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			int i = 0;
			Course c[] = new Course[10];
			while (data.next()) { // (int i, String cn, int pi, String pn, boolean a, ArrayList<Integer> arr)
				c[i++] = studCourse(data.getInt("courseID"));
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Course studCourse(int cid) {
		String sql = "SELECT * FROM coursetable WHERE active = 1 AND id = " + cid;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			if (data.next()) { // (int i, String cn, int pi, String pn, boolean a, ArrayList<Integer> arr)
				Course c = new Course(cid, data.getString("name"), data.getInt("profID"), null, true, null);
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Assignment> profAssigns(int id) {
		String sql = "SELECT * FROM assignmenttable WHERE courseID = " + id;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			// ctor (int i,int ci, String t, boolean a, String p, String d)
			// table (id, courseID, title, path, active, dueDate)
			ArrayList<Assignment> a = new ArrayList<Assignment>();
			while (data.next()) {
				a.add(new Assignment(data.getInt("id"), data.getInt("courseID"), data.getString("title"),
						data.getBoolean("active"), data.getString("path"), data.getString("duedate")));
			}
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Assignment> studentAssigns(int id) {
		String sql = "SELECT * FROM assignmenttable WHERE active = 1 AND courseID = " + id;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			// ctor (int i,int ci, String t, boolean a, String p, String d)
			// table (id, courseID, title, path, active, dueDate)
			ArrayList<Assignment> a = new ArrayList<Assignment>();
			while (data.next()) {
				a.add(new Assignment(data.getInt("id"), data.getInt("courseID"), data.getString("title"),
						data.getBoolean("active"), data.getString("path"), data.getString("duedate")));
			}
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<User> getStudents() {
		String sql = "SELECT * FROM usertable WHERE type = 's'";
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			// table (id, password, email, firstName, lastName, type)
			// ctor User(int i,String p, String e, String f, String l,char t)
			ArrayList<User> a = new ArrayList<User>();
			while (data.next()) { // (int i,String p, String e, String f, String l, a, char t)
				a.add(new User(data.getInt("id"), null, data.getString("email"), data.getString("firstName"),
						data.getString("lastName"), getStudentCourses(data.getInt("id")),
						data.getString("type").charAt(0)));
			}
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Integer> getStudentCourses(int id) {
		String sql = "SELECT * FROM studentenrollmenttable WHERE studentID = " + id;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			ArrayList<Integer> a = new ArrayList<Integer>();
			while (data.next()) {
				a.add(data.getInt("courseID"));
			}
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addAssignment(int id, int cid, String title, String path, String duedate) {
		boolean b = false;
		String sql = "INSERT INTO assignmenttable (id, courseID, title, path, active, dueDate) VALUES ('"
				+ Integer.toString(id) + "', '" + Integer.toString(cid) + "', '" + title + "', '" + path + "', " + b
				+ ", '" + duedate + "')";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeAssignment(int id) {
		String sql = "DELETE FROM assignmenttable WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void activateAssign(int id) {
		boolean b = true;
		String sql = "UPDATE assignmenttable SET active = " + b + " WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addCourse(int id, int pid, String name) {
		boolean b = false;
		String sql = "INSERT INTO coursetable (id, profID, name, active) VALUES ('" + Integer.toString(id) + "', '"
				+ Integer.toString(pid) + "', '" + name + "'," + b + ")";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeCourse(int id) {
		String sql = "DELETE FROM coursetable WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void activateCourse(int id) {
		boolean b = true;
		String sql = "UPDATE coursetable SET active = " + b + " WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addGrade(int id, int aid, int sid, int cid, int grade) {
		String sql = "INSERT INTO gradetable (id, assignID, studentID, courseID, assignmentGrade) VALUES ('"
				+ Integer.toString(id) + "', '" + Integer.toString(aid) + "', '" + Integer.toString(sid) + "', '"
				+ Integer.toString(cid) + "', '" + Integer.toString(grade) + "')";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeGrade(int id) {
		String sql = "DELETE FROM gradetable WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addEnrollment(int id, int sid, int cid) {
		String sql = "INSERT INTO studentenrollmenttable (id, studentID, courseID) VALUES ('" + Integer.toString(id)
				+ "', '" + Integer.toString(sid) + "', '" + Integer.toString(cid) + "')";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeEnrollment(int sid, int cid) {
		String sql = "DELETE FROM studentenrollmenttable WHERE studentID = '" + Integer.toString(sid)
				+ "' AND courseID = " + "'" + Integer.toString(cid) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Submission> getSubmissionsProf(int aid) {
		String sql = "SELECT * FROM submissiontable WHERE assignID = " + aid;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			// table (id, password, email, firstName, lastName, type)
			// ctor User(int i,String p, String e, String f, String l,char t)
			ArrayList<Submission> a = new ArrayList<Submission>();
			while (data.next()) { // (int i, int ai, int si, String t, String p, String ts, int g, String c)
				a.add(new Submission(data.getInt("id"), data.getInt("assignID"), data.getInt("studentID"),
						data.getString("title"), data.getString("path"), data.getString("timestamp"),
						data.getInt("submissionGrade"), data.getString("comments")));
			}
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Submission getSubmissionStudent(int aid, int sid) {
		String sql = "SELECT * FROM submissiontable WHERE assignID = " + aid + " AND studentID = " + sid;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			// table (id, password, email, firstName, lastName, type)
			// ctor User(int i,String p, String e, String f, String l,char t)
			if (data.next()) { // (int i, int ai, int si, String t, String p, String ts, int g, String c)
				return new Submission(data.getInt("id"), data.getInt("assignID"), data.getInt("studentID"),
						data.getString("title"), data.getString("path"), data.getString("timestamp"),
						data.getInt("submissionGrade"), data.getString("comments"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Submission getSubmission(int sid) {
		String sql = "SELECT * FROM submissiontable WHERE id = " + sid;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			// table (id, password, email, firstName, lastName, type)
			// ctor User(int i,String p, String e, String f, String l,char t)
			if (data.next()) { // (int i, int ai, int si, String t, String p, String ts, int g, String c)
				return new Submission(data.getInt("id"), data.getInt("assignID"), data.getInt("studentID"),
						data.getString("title"), data.getString("path"), data.getString("timestamp"),
						data.getInt("submissionGrade"), data.getString("comments"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addSubmission(int id, int aid, int sid, String path, String title, int grade, String comment,
			String timestamp) {
		String sql = "INSERT INTO submissiontable (id, assignID, studentID, path, title, submissionGrade, comments, timestamp) VALUES ('"
				+ Integer.toString(id) + "', '" + Integer.toString(aid) + "', '" + Integer.toString(sid) + "', '" + path
				+ "', '" + title + "', '" + Integer.toString(grade) + "', '" + comment + "', '" + timestamp + "')";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void gradeSubmission(int id, int grade, String com) {
		String sql = "UPDATE submissiontable SET submissionGrade = " + grade + ", comments = '" + com + "' WHERE id = '"
				+ Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeSubmission(int id) {
		String sql = "DELETE FROM submissiontable WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addUser(int id, String pass, String email, String fname, String lname, char t) {
		String sql = "INSERT INTO usertable (id, password, email, firstName, lastName, type) VALUES ("
				+ Integer.toString(id) + ", '" + pass + "', '" + email + "', '" + fname + "', '" + lname + "', '" + t
				+ "')";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void removeUser(int id) {
		String sql = "DELETE FROM usertable WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User voidSearchStudent(int id) {
		String sql = "SELECT * FROM usertable WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (!result.first()) {
				return null;
			}
			User student = new User(result.getInt("id"), null, result.getString("email"), result.getString("firstName"),
					result.getString("lastName"), 's');
			return student;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<User> voidSearchStudent(String lname) {
		String sql = "SELECT * FROM usertable WHERE lastName = '" + lname + "'";
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (!result.first()) {
				return null;
			}
			result.beforeFirst();
			ArrayList<User> students = new ArrayList<User>();
			while (result.next()) {
				User student = new User(result.getInt("id"), null, result.getString("email"),
						result.getString("firstName"), result.getString("lastName"), 's');
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
