<<<<<<< HEAD
package server;
=======
package Server;

>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.net.ServerSocket;
import java.util.concurrent.*;
import java.net.InetAddress;

/**
 * A class to connect users to a server
 */
public class Server {

<<<<<<< HEAD
private ServerSocket serverSocket;
private ExecutorService exService = Executors.newFixedThreadPool(20);

/**
 * creates multiple threads to allow DB/file/email access
 */
private void runServer() {
        int serverPort = 6969;
        try {
            System.out.println("Starting Server");
            System.out.println("IP = " + InetAddress.getLocalHost());
            serverSocket = new ServerSocket(serverPort, 20, InetAddress.getLocalHost());
            while(true) {
                try {
                  Socket s = serverSocket.accept();
                  exService.submit(new Worker(s));
                }
                 catch(IOException e) {
                    System.out.println("Error accepting connection");
                } catch(SQLException e) {
                   System.out.println("Error accepting connection");
               }
            }
        }catch(IOException e) {
            System.out.println("Error starting Server");
        }
}


public static void main(String[] args) {
  Server svr = new Server ();
  svr.runServer();
  System.out.println("Server ended");
}
=======
	private ServerSocket serverSocket;
	private ExecutorService exService = Executors.newFixedThreadPool(20);

	/**
	 * creates multiple threads to allow DB/file/email access
	 */
	private void runServer() {
		int serverPort = 6969;
		try {
			System.out.println("Starting Server");
			System.out.println("IP = " + InetAddress.getLocalHost());
			serverSocket = new ServerSocket(serverPort, 20, InetAddress.getLocalHost());
			while (true) {
				try {
					Socket s = serverSocket.accept();
					exService.submit(new Worker(s));
				} catch (IOException e) {
					System.out.println("Error accepting connection");
				} catch (SQLException e) {
					System.out.println("Error accepting connection");
				}
			}
		} catch (IOException e) {
			System.out.println("Error starting Server");
		}
	}

	public static void main(String[] args) {
		Server svr = new Server();
		svr.runServer();
		System.out.println("Server ended");
	}
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45

}
