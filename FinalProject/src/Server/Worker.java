<<<<<<< HEAD
package server;
import objects.*;
=======
package Server;
import Objects.*;
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.sql.SQLException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Worker implements Runnable{

//	User user;
	ObjectInputStream objIn;
	ObjectOutputStream objOut;
	Socket clientSocket;
	DBHelper databaseHelper;
	FileHelper filehelper;
	// EmailHelper emailHelper;
	String command;

	/**
	 * constructor for Worker
	 * @param cS client's socket
	 */
  public Worker(Socket cS) throws IOException, SQLException {
		  clientSocket = cS;
			objOut = new ObjectOutputStream(clientSocket.getOutputStream());
    	objOut.flush();
  		objIn = new ObjectInputStream(clientSocket.getInputStream());
			databaseHelper = new DBHelper();
			filehelper = new FileHelper();
			// emailHelper = new EmailHelper();
	}

	/**
 	 * runs the thread until the client's window is closed
 	 */
	public void run() {
		command = "start";
		while(!command.equals("quit")){
			try{
			command = (String) objIn.readObject();
		switch (command) {
			case "Login":
				loginAttempt();
				break;
			case "NewCourse":
				newCourse();
				break;
			case "ActivateC":
				activateCourse();
				break;
			case "StudentSearchID":
				studentSearchID();
				break;
			case "StudentSearchLN":
				studentSearchLN();
				break;
			case "Enroll":
				enroll();
				break;
			case "Unenroll":
				unenroll();
				break;
			case "UploadA":
				uploadAssign();
				break;
			case "UploadS":
				uploadSub();
				break;
			case "DownloadS":
				downloadSub();
				break;
			case "DownloadA":
				downloadAssign();
				break;
			case "ActivateA":
				activateAssign();
				break;
<<<<<<< HEAD
			case "getProfEmail":
				profEmail();
=======
			case "EmailC":
				emailCourse();
				break;
			case "Email":
				email();
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
				break;
			case "Grade":
				gradeAssign();
				break;
			default:
				break;
		}

			}catch(IOException e){System.out.println("Command error");}
			catch(ClassNotFoundException e){}
		}
		try{
			System.out.println("closing thread");
			objIn.close();
			objOut.close();
			clientSocket.close();
		}catch(IOException e){System.out.println("IO Closing error");}
	}

	/**
 	 * checks ID & Pass to check validity,
	 * if valid, sends the according info
 	 */
	public void loginAttempt(){
		try{
		Account account = (Account) objIn.readObject();
		User p = databaseHelper.checkLogin(account.getId(),account.getPass());
		if(p != null){
		objOut.writeObject("Yes");
		// send user
		objOut.writeObject(p);
		objOut.flush();
		if(p.getType() == 't'){
		sendProf(p);}
		else{
		sendStudent(p);}
		return;
		}
		objOut.writeObject("No");
		objOut.flush();
		return;
	}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	/**
	 *
	 */
	public void sendProf(User p){
		try{
		// send courses[]
		Course c[] = databaseHelper.profCourses(p);
		objOut.writeObject(c);
		// send Assigns
		ArrayList<Assignment> assigns = new ArrayList<Assignment>();
		for(int i = 0; i<c.length; i++){
			if(c[i] == null){break;}
			assigns.addAll(databaseHelper.profAssigns(c[i].getId()));
		}
		objOut.writeObject(assigns);
		// send students
		ArrayList<User> students = databaseHelper.getStudents();
		objOut.writeObject(students);
		// send submissions
		ArrayList<Submission> subs = new ArrayList<Submission>();
		for(int i = 0; i<assigns.size(); i++){
			if(assigns.get(i) == null){break;}
			subs.addAll(databaseHelper.getSubmissionsProf(assigns.get(i).getId()));
		}
		objOut.writeObject(subs);
		objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
	}

	/**
	 *
	 */
	public void sendStudent(User s){
		try{
		// send courses[]
		Course c[] = databaseHelper.studCourses(s);
		objOut.writeObject(c);
		// send Assigns
		ArrayList<Assignment> assigns = new ArrayList<Assignment>();
		for(int i = 0; i<c.length; i++){
			if(c[i] == null){break;}
			assigns.addAll(databaseHelper.studentAssigns(c[i].getId()));
		}
		objOut.writeObject(assigns);
		// send subs
		ArrayList<Submission> subs = new ArrayList<Submission>();
		for(int i = 0; i<assigns.size(); i++){
			if(assigns.get(i) == null){break;}
			subs.add(databaseHelper.getSubmissionStudent(assigns.get(i).getId(),s.getId()));
		}
		objOut.writeObject(subs);
		objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
	}

	/**
	 *
	 */
	public void newCourse(){
		try{
			Integer cID = (Integer) objIn.readObject();
			Integer uID = (Integer) objIn.readObject();
			String cName = (String) objIn.readObject();
			databaseHelper.addCourse(cID, uID, cName);
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

 /**
  * activates the course with the receiced course ID
  */
	public void activateCourse(){
		try{
			Integer cID = (Integer) objIn.readObject();
			databaseHelper.activateCourse(cID);
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void enroll(){
		try{
			Integer sid = (Integer) objIn.readObject();
			Integer cid = (Integer) objIn.readObject();
			databaseHelper.addEnrollment(0,sid,cid);
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void unenroll(){
		try{
			Integer sid = (Integer) objIn.readObject();
			Integer cid = (Integer) objIn.readObject();
			databaseHelper.removeEnrollment(sid,cid);
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void uploadAssign(){
		try{
			Assignment assign = (Assignment) objIn.readObject();
			filehelper.saveAssignServer(assign);
			databaseHelper.addAssignment(assign.getId(), assign.getCourseID(), assign.getTitle(), assign.getPath(), assign.getDueDate());
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void	activateAssign(){
		try{
			Integer aID = (Integer) objIn.readObject();
			databaseHelper.activateAssign(aID);
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void studentSearchID(){
		try{
			Integer id = (Integer) objIn.readObject();
			User student = databaseHelper.voidSearchStudent(id);
			objOut.writeObject(student);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void studentSearchLN(){
		try{
			String ln = (String) objIn.readObject();
			ArrayList<User> students = new ArrayList<>(databaseHelper.voidSearchStudent(ln));
			objOut.writeObject(students);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

<<<<<<< HEAD
	public void	profEmail(){
		try{
			int sid = (int) objIn.readObject();
			objOut.writeObject(databaseHelper.getProfEmail(sid));
			objOut.flush();
		}
=======
	public void	emailCourse(){
System.out.println("emailCourse");
	}

	public void	email(){
System.out.println("email");
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
	}

	public void	gradeAssign(){
		try{
			int sid = (int) objIn.readObject();
			int g = (int) objIn.readObject();
			String c = (String) objIn.readObject();
			databaseHelper.gradeSubmission(sid,g,c);
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

	public void	uploadSub(){
<<<<<<< HEAD
		try{
			Submission sub = (Submission) objIn.readObject();
			filehelper.saveSubmissionServer(sub);
			databaseHelper.addSubmission(sub.getId(), sub.getAssignId(), sub.getStudentId(), sub.getPath(),
			sub.getTitle(), sub.getGrade(), sub.getComment(), sub.getTimeStamp());
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}

=======

	}
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
	public void	downloadSub(){
		try{
			int sid = (int) objIn.readObject();
			Submission sub = databaseHelper.getSubmission(sid);
			byte[] content = filehelper.createByteArray(new File(sub.getPath()));
			sub.setByte(content);
			objOut.writeObject(sub);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	}
<<<<<<< HEAD
	public void	downloadAssign(){ // fix for Assign
		try{
			int aid = (int) objIn.readObject();
			Assignment assign = databaseHelper.getAssignment(aid);
			byte[] content = filehelper.createByteArray(new File(Assign.getPath()));
			assign.setByte(content);
			objOut.writeObject(assign);
			objOut.flush();
		}catch(IOException e){System.out.println(e.getMessage());}
		catch(ClassNotFoundException e){System.out.println(e.getMessage());}
=======
	public void	downloadAssign(){

>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
	}


}
