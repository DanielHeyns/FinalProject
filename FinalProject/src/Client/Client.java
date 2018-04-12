package client;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.net.Socket;
import java.net.InetAddress;
import java.util.ArrayList;
import objects.*;

public class Client {

Login loginGUI;
//public ProfGui profGUI;
//public StudentGui studentGUI;

ObjectInputStream objIn;
ObjectOutputStream objOut;
Course courses[];
ArrayList<Student> students;
ArrayList<Assignment> assigns;
User user;


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
		socket = new Socket(InetAddress.getByName("192.168.0.22"), 6969);
		objOut = new ObjectOutputStream(socket.getOutputStream());
		objOut.flush();
		objIn = new ObjectInputStream(socket.getInputStream());
	} catch(IOException e){System.out.println("socket IO Error");}
		loginGUI = new Login(this);
	}

	/**
	 * A func to check if the login info is valid
	 */
	public boolean login(int id, String pass){
		String str;
		try{
			objOut.writeObject("Login");
			objOut.flush();
			// objOut.writeObject(new Account(id,pass));
		// 	objOut.flush();
			str = (String) objIn.readObject();
		} catch(IOException e){return false;}
		if(str.equals("Yes")){
			return true;
		}else{return false;}
	}

	/*public void setUser(){
		user = p;
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
		databaseHelper.addCourse(c.getId(), user.getId(), c.getCourseName());
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
		databaseHelper.addAssignment(assign.getId(), assign.getCourseID(), assign.getTitle(), assign.getPath(), assign.getDueDate());
	}*/



}
