package server;
import objects.*;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Worker implements Runnable{

	User user;
	ObjectInputStream objIn;
	ObjectInputStream objOut;
	Socket clientSocket;
	DBHelper databaseHelper;
	FileHelper filehelper;
	// EmailHelper emailHelper;
	String command;

	/**
	 * constructor for Worker
	 * @param cS client's socket
	 */
  public Worker(Socket cS) throws IOException {
		  clientSocket = cS;
			objOut = new ObjectOutputStream(clientSocket.getOutputStream());
    	objOut.flush();
  		objIn = new ObjectInputStream(clientSocket.getInputStream());
			databaseHelper = new DBHelper();
			filehelper = new FileHelper();
			// emailHelper = new EmailHelper();
	}

	public void run() {
		command = "start";
		while(!command.equals("quit")){
			try{
			command = (String) objIn.readObject();
		switch (command) {
			case null:
				break;
			case: "Login":
				loginAttempt();
				break;
			case: "NewCourse":
				newCourse();
				break;
			case: "ActivateC"
				activateCourse();
				break;
			case: "Enroll"
				enroll();
				break;
			case: "Unenroll"
				unenroll();
				break;
			case: "Upload"
				uploadAssign();
				break;
			case: "ActivateA"
				activateAssign();
				break;
			case: "EmailC"
				emailCourse();
				break;
			case: "Email"
				email();
				break;
			case: "Grade"
				gradeAssign();
				break;
			case: "Submit"
				submission();
			default:
				break;
		}

			}catch(IOException e){System.out.println("Command error");}
		}
		try{
			objIn.close();
			objOut.close();
			clientSocket.close();
		}catch(IOException e){System.out.println("IO Closing error");}
	}

	public void loginAttempt(){
		System.out.println("loginAttempt");
		objOut.writeObject("No");
		objOut.flush();
	}

	public void newCourse(){
		System.out.println("newCourse");
	}

	public void activateCourse(){
		System.out.println("activateCourse");
	}

	public void enroll(){
System.out.println("enroll");
	}

	public void unenroll(){
System.out.println("unenroll");
	}

	public void uploadAssign(){
System.out.println("uploadAssign");
	}

	public void	activateAssign(){
System.out.println("activateAssign");
	}

	public void	emailCourse(){
System.out.println("emailCourse");
	}

	public void	email(){
System.out.println("email");
	}

	public void	gradeAssign(){
System.out.println("gradeAssign");
	}

	public void	submission(){
System.out.println("submission");
	}


}
