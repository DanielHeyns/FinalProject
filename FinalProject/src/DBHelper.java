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

	public static void main(String args[]) throws SQLException {
		DBHelper easy = new DBHelper();
		easy.removeGrade(13);
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
				+ Integer.toString(pid) + "', '" + name + "',"+ b + ")";
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
				+ Integer.toString(id) + "', '" + Integer.toString(aid) + "', '" + Integer.toString(sid) + "', '" + Integer.toString(cid) + "', '"
				+ Integer.toString(grade) + "')";
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
}
