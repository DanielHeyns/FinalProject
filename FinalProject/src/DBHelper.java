import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	private Connection jdbc_connection;
	private Statement statement;
	private String databaseName = "schooldb";

	private String connectioninfo = "jdbc:mysql://localhost:3306/" + databaseName, login = "root", password = "simple";

	private DBHelper() throws SQLException {
		try {
			jdbc_connection = DriverManager.getConnection(connectioninfo, login, password);
			System.out.println("Successful Connection to:" + connectioninfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public void removeEnrollment(int id) {
		String sql = "DELETE FROM studentenrollmenttable WHERE id = '" + Integer.toString(id) + "'";
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public void gradeSubmission(int id, int grade) {
		String sql = "UPDATE submissiontable SET submissionGrade = " + grade + " WHERE id = '" + Integer.toString(id)
				+ "'";
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
		String sql = "INSERT INTO usertable (id, password, email, firstName, lastName, type) VALUES ('"
				+ Integer.toString(id) + "', '" + pass + "', '" + email + "', '" + fname + "', '" + lname + "', '" + t
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

}
