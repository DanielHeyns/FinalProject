package client;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.net.Socket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.net.Socket;
import java.net.InetAddress;
import java.io.*;
import objects.*;

public class Client {

Login loginGUI;
public ProfGui profGUI;
public StudentGUI studentGUI;
EmailHelper emailhelper;

ObjectInputStream objIn;
ObjectOutputStream objOut;
Socket socket;
Course courses[];
ArrayList<User> students;
ArrayList<Assignment> assigns;
ArrayList<Submission> submissions;
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
			objOut.writeObject(new Account(id,pass));
		  objOut.flush();
			str = (String) objIn.readObject();
		} catch(IOException e){return false;}
		catch(ClassNotFoundException e){return false;}
		if(str.equals("Yes")){
			setUser();
			emailhelper = new EmailHelper(this);
			return true;
		}else{return false;}
	}



	/**
	 *	A func to "cut-off" communication with the server
	 */
	public void closeClient(){
	if(!socket.isClosed()){
		try{
			objOut.writeObject("quit");
			objOut.flush();
			objIn.close();
			objOut.close();
			socket.close();
		} catch(IOException e){return;}
	}
	}

	/**
	 *	A func to initialize the user's info
	 */
	public void setUser(){
		try{
		user = (User) objIn.readObject();
		if(user.getType() == 't'){ // login info is from a prof
			profGUI = new ProfGui(this);
			setProf();}
		else{ // login info is from a student
		 studentGUI = new StudentGUI(this);
		 setStudent();}
	}catch(IOException e){System.out.println(e.getMessage());}
	catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void setProf() {
		try{
			courses = (Course[]) objIn.readObject();
			assigns = (ArrayList<Assignment>) objIn.readObject();
			students = (ArrayList<User>) objIn.readObject();
			submissions = (ArrayList<Submission>) objIn.readObject();
			profGUI.displayUser();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void setStudent(){
		try{
			courses = (Course[]) objIn.readObject();
			assigns = (ArrayList<Assignment>) objIn.readObject();
			submissions = (ArrayList<Submission>) objIn.readObject();
			studentGUI.displayUser();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void addCourse(Course c){
	try{
		objOut.writeObject("NewCourse");
		objOut.writeObject(c.getId());
		objOut.writeObject(user.getId());
		objOut.writeObject(c.getCourseName());
		objOut.flush();
	}catch(IOException e){System.out.println(e.getMessage());}
		for(int i = 0; i<courses.length; i++){
			if(courses[i] == null){courses[i] = c; break;}
		}
	}

	public void activateCourse(int id){
		try{
			objOut.writeObject("ActivateC");
			objOut.writeObject(id);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		for(int i = 0; i<courses.length; i++){
			if(courses[i].getId() == id){courses[i].activate(); break;}
		}
		profGUI.listener.updateCourses();
	}

	public void activateAssign(int id){
		try{
			objOut.writeObject("ActivateA");
			objOut.writeObject(id);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		for(int i = 0; i<assigns.size(); i++){
			if(assigns.get(i).getId() == id){assigns.get(i).activate(); break;}
		}
		profGUI.listener.updateAssigns();
	}


	public void enrollStudent(int sid,int cid){
		try{
			objOut.writeObject("Enroll");
			objOut.writeObject(sid);
			objOut.writeObject(cid);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		for(int i = 0; i<students.size(); i++){
			if(students.get(i).getId() == sid){students.get(i).addCourse(cid); break;}
		}
		profGUI.listener.updateStudents();
	}

	public void unenrollStudent(int sid,int cid){
		try{
			objOut.writeObject("Unenroll");
			objOut.writeObject(sid);
			objOut.writeObject(cid);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		for(int i = 0; i<students.size(); i++){
			if(students.get(i).getId() == sid){students.get(i).removeCourse(cid); break;}
		}
		profGUI.listener.updateStudents();
	}


	public void searchStudentID(int id){
		User student = null;
		try{
			objOut.writeObject("StudentSearchID");
			objOut.writeObject(id);
			objOut.flush();
			student = (User) objIn.readObject();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){}
		if(student != null){
		profGUI.sListModel.removeAllElements();
		profGUI.sListModel.addElement(student.toString());
		}
	}

	public void searchStudentLN(String ln){
		ArrayList<User> students = null;
		try{
			objOut.writeObject("StudentSearchLN");
			objOut.writeObject(ln);
			objOut.flush();
			students = (ArrayList<User>) objIn.readObject();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){}
		if(students != null){
		profGUI.sListModel.removeAllElements();
		for(int i = 0; i<students.size(); i++)
			profGUI.sListModel.addElement(students.get(i).toString());
		}
	}

	public void uploadAssign(Assignment assign) {
		try{
			objOut.writeObject("UploadA");
			objOut.writeObject(assign);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		assigns.add(assign);
		profGUI.listener.updateAssigns();
	}

	public void uploadSub(Submission sub) {
		try{
			objOut.writeObject("UploadS");
			objOut.writeObject(sub);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		submissions.add(sub);
	}

	public String profSubmissionString(int aid){
		for(int i = 0; i<submissions.size(); i++){
			if(submissions.get(i) == null){return "-- No submition received --";}
			if(submissions.get(i).getAssignId() == aid){
				return submissions.get(i).toString();
			}
		}
		return "-- No submition received --";
	}

	public void gradeSubmission(int sid,int g, String comment){
		try{
			objOut.writeObject("Grade");
			objOut.writeObject(sid);
			objOut.writeObject(g);
			objOut.writeObject(comment);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		for(int i = 0; i<submissions.size(); i++){
			if(submissions.get(i) == null){break;}
			if(submissions.get(i).getId() == sid){
				submissions.get(i).setGrade(g);
				submissions.get(i).setComments(comment);
				break;
			}
		}
		profGUI.listener.updateAssigns();
	}

	public void downloadSub(int sid){
		try{
			objOut.writeObject("DownloadS");
			objOut.writeObject(sid);
			objOut.flush();
			FileHelper filehelper = new FileHelper();
			Submission s = (Submission) objIn.readObject();
		//	filehelper.saveSubmissionClient(s);
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void downloadAssign(int sid){
		try{
			objOut.writeObject("DownloadA");
			objOut.writeObject(sid);
			objOut.flush();
			FileHelper filehelper = new FileHelper();
			Assignment s = (Assignment) objIn.readObject();
		//	filehelper.saveSubmissionClient(s);
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void emailCourse(int cid){
		emailhelper.createMessage(getCourseEmails(cid));
	}

	public void emailProf(int pid){
		emailhelper.createMessage(getProfEmail(pid));
	}

	public String getCourseEmails(int cid){
		String str = "";
		for(int i = 0; i<students.size(); i++){
			if(students.get(i).inCourse(id))
			 str += (students.get(i).getEmail() + ";");
		}
		return str;
	}

	public String getProfEmail(int pid){
		String str = "";
		try{
			objOut.writeObject("getProfEmail");
			objOut.writeObject(pid);
			objOut.flush();
			str = (String) objIn.readObject();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
		return str;
	}

}
