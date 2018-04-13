package Client;
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JOptionPane;
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
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmLogin;
	//receive ID
	private JTextField textFieldID;
	//receive password
	private JPasswordField textFieldPass;
	LoginListener listener;
	/**
	 * Create the application.
	 */
	public Login(Client c) {
		initialize(c);
		frmLogin.setVisible(true); // show login window at start
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Client client) {
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
		lblPassword.setBounds(131, 95, 120, 20);
		loginP.add(lblPassword);

		//field to receive password
		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(219, 93, 146, 26);
		loginP.add(textFieldPass);
		textFieldPass.setColumns(10);

		listener = new LoginListener(client,frmLogin, textFieldID, textFieldPass,
																	btnLogin);
		btnLogin.addActionListener(listener);

		frmLogin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeClient();
            }
						@Override
						public void windowClosed(WindowEvent e) {
								client.closeClient();
						}
        });
	}

	// __________________ LoginListener __________________ //

	/**
	 * A class to listen to the login GUI elements
	 */
	public class LoginListener implements ActionListener {
		private JFrame frame;
		private Client client;
		private JTextField idField;
		private JPasswordField passField;
		private JButton button;

	public LoginListener(Client c, JFrame f, JTextField t, JPasswordField p,
												JButton b){
	frame = f; client = c; idField = t; passField = p; button = b;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			int id = Integer.parseInt(idField.getText().trim());
			String pass = passField.getText().trim();
			checkLogin(id,pass);
		}
	}

	 private void checkLogin(int id, String pass){
		Boolean check = client.login(id, pass);
		if(check){
			frame.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect ID/Password." +
				"\nPlease try again.", "Unable to login",  JOptionPane.PLAIN_MESSAGE);
		}
	}
	}
}
