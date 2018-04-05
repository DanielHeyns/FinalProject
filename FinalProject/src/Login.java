import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frmLogin;
	//receive ID
	private JTextField textFieldID;
	//receive password
	private JPasswordField textFieldPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setFont(new Font("Wingdings 2", Font.PLAIN, 12));
		frmLogin.setTitle("Login Window");
		frmLogin.setBounds(100, 100, 532, 287);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel buttonP = new JPanel();
		frmLogin.getContentPane().add(buttonP, BorderLayout.SOUTH);
		
		//button to log in
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		buttonP.add(btnLogin);
		
		JPanel loginP = new JPanel();
		frmLogin.getContentPane().add(loginP, BorderLayout.CENTER);
		loginP.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblId.setBounds(189, 54, 69, 20);
		loginP.add(lblId);
		
		//text field to receive the id of the login
		textFieldID = new JTextField();
		textFieldID.setBounds(219, 51, 146, 26);
		loginP.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:\r\n");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPassword.setBounds(131, 95, 85, 20);
		loginP.add(lblPassword);
		
		//field to receive password
		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(219, 93, 146, 26);
		loginP.add(textFieldPass);
		textFieldPass.setColumns(10);
	}
}
