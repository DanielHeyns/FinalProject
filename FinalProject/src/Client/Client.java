package Client;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import Objects.*;

public class Client {

public ProfGui profGUI;
// private ObjectInputStream objIn;
// private ObjectOutputStream objOut;
Course courses[];
ArrayList<Student> students;
ArrayList<Assignment> assigns;
public DBHelper databaseHelper;
Professor professor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client client = new Client();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Client (){
	try{
		databaseHelper = new DBHelper();
	}catch(SQLException e) { e.printStackTrace(); System.exit(1); }
		profGUI = new ProfGui(this);
	}
	public void setUser(Professor p){
		professor = p;
		courses = databaseHelper.profCourses(p);
		assigns = new ArrayList<Assignment>();
		students = databaseHelper.getStudents();
		for(int i = 0; i<courses.length; i++){
			if(courses[i] == null){break;}
			assigns.addAll(databaseHelper.profAssigns(courses[i].getId()));
		}
		profGUI.displayUser();
	}
	public void addCourse(Course c){
		databaseHelper.addCourse(c.getId(), professor.getId(), c.getCourseName());
		for(int i = 0; i<courses.length; i++){
			if(courses[i] == null){courses[i] = c; break;}
		}
	}
	public void activateCourse(int id){
		databaseHelper.activateCourse(id);
		for(int i = 0; i<courses.length; i++){
			if(courses[i].getId() == id){courses[i].activate(); break;}
		}
		profGUI.listener.updateCourses();
	}

	public void activateAssign(int id){
		databaseHelper.activateAssign(id);
		for(int i = 0; i<assigns.size(); i++){
			if(assigns.get(i).getId() == id){assigns.get(i).activate(); break;}
		}
		profGUI.listener.updateAssigns();
	}


	public void enrollStudent(int sid,int cid){
		databaseHelper.addEnrollment(0,sid,cid);
		for(int i = 0; i<students.size(); i++){
			if(students.get(i).getId() == sid){students.get(i).addCourse(cid); break;}
		}
		profGUI.listener.updateStudents();
	}

	public void unenrollStudent(int sid,int cid){
		databaseHelper.removeEnrollment(sid,cid);
		for(int i = 0; i<students.size(); i++){
			if(students.get(i).getId() == sid){students.get(i).removeCourse(cid); break;}
		}
		profGUI.listener.updateStudents();
	}
	public void uploadFile(Assignment assign) {
		assigns.add(assign);
		profGUI.listener.updateAssigns();
	// instead of sending directly to the database here, we need to use FileHelper.sendFile();
	//	databaseHelper.addAssignment(assign.getId(), assign.getCourseID(), assign.getTitle(), assign.getPath(), assign.getDueDate());
	}



}
