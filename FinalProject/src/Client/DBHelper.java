package Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import objects.*;

public class DBHelper {
	private Connection jdbc_connection;
	private Statement statement;
	private String databaseName = "SchoolDB";

	private String connectioninfo = "jdbc:mysql://localhost:3306/" + databaseName,
	 							 login = "root", password = "123mysql";

	public DBHelper() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			jdbc_connection = DriverManager.getConnection(connectioninfo, login, password);
			System.out.println("Successful Connection to:" + connectioninfo);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e ){e.printStackTrace();}
	}

	public Professor checkLogin(int id, String pass)
	{
		String sql = "SELECT * FROM usertable WHERE ID = " + id;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			if(data.next())
			{ //public Professor(int i,String p, String e, String f, String l)
				Professor p = new Professor(id, data.getString("password"),
								data.getString("email"), data.getString("firstName"),
								data.getString("lastName"));
				if(!p.samePass(pass)){return null;}
				return p;
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	public Course[] profCourses(Professor p)
	{
		String sql = "SELECT * FROM coursetable WHERE profID = " + p.getId();
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			int i = 0;
			Course c[] = new Course[5];
			while(data.next())
			{ // (int i, String cn, int pi, String pn, boolean a, ArrayList<Integer> arr)
				c[i++] = new Course(data.getInt("id"), data.getString("name"),
								p.getId(), p.getFirstName(), data.getBoolean("active"),null);
			}
			return c;
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	public ArrayList<Assignment> profAssigns(int id)
	{
		String sql = "SELECT * FROM assignmenttable WHERE courseID = " + id;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			// ctor (int i,int ci, String t, boolean a, String p, String d)
			// table (id, courseID, title, path, active, dueDate)
			ArrayList<Assignment> a = new ArrayList<Assignment>();
			while(data.next())
			{
				a.add(new Assignment(data.getInt("id"), data.getInt("courseID"),
								data.getString("title"),data.getBoolean("active"),
								 data.getString("path"), data.getString("duedate")));
			}
			return a;
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	public ArrayList<Student> getStudents()
	{
		String sql = "SELECT * FROM usertable WHERE type = 's'";
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			// table (id, password, email, firstName, lastName, type)
			// ctor (int i,String p, String e, String f, String l)
			ArrayList<Student> a = new ArrayList<Student>();
			while(data.next())
			{
				a.add(new Student(data.getInt("id"), null, data.getString("email"),
				data.getString("firstName"), data.getString("lastName"),
				getStudentcourses(data.getInt("id"))));
			}
			return a;
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	public ArrayList<Integer> getStudentcourses(int id)
	{
		String sql = "SELECT * FROM studentenrollmenttable WHERE studentID = " + id;
		ResultSet data;
		try {
			statement = jdbc_connection.createStatement();
			data = statement.executeQuery(sql);
			ArrayList<Integer> a = new ArrayList<Integer>();
			while(data.next())
			{
				a.add(data.getInt("courseID"));
			}
			return a;
		} catch (SQLException e) { e.printStackTrace(); }
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
		String sql = "DELETE FROM studentenrollmenttable WHERE studentID = '" +
						Integer.toString(sid) + "' AND courseID = " +
						 "'" + Integer.toString(cid) + "'";
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

	public Student voidSearchStudent(int id) {
			String sql = "SELECT * FROM usertable WHERE id = '" + Integer.toString(id) + "'";
			try {
				statement = jdbc_connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				if (!result.first()) {
					return null;
				}
				Student student = new Student(result.getInt("id"), null, result.getString("email"),
						result.getString("firstName"), result.getString("lastName"));
				return student;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		public ArrayList<Student> voidSearchStudent(String lname) {
			String sql = "SELECT * FROM usertable WHERE lastName = '" + lname + "'";
			try {
				statement = jdbc_connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				if (!result.first()) {
					return null;
				}
				result.beforeFirst();
				ArrayList<Student> students = new ArrayList<Student>();
				while (result.next()) {
					Student student = new Student(result.getInt("id"), null, result.getString("email"),
							result.getString("firstName"), result.getString("lastName"));
					students.add(student);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;

		}

}
