package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class EmailMessageWindow {

	 JFrame frmSendEmail;
	 JTextField textField;
	 JTextField textField_1;
	 JButton btnSend;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailMessageWindow window = new EmailMessageWindow();
					window.frmSendEmail.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmailMessageWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSendEmail = new JFrame();
		frmSendEmail.setTitle("Send Email");
		frmSendEmail.setBounds(100, 100, 743, 531);
		frmSendEmail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSendEmail.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel TopP = new JPanel();
		frmSendEmail.getContentPane().add(TopP, BorderLayout.NORTH);
		TopP.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTo = new JLabel("To:");
		TopP.add(lblTo);
		
		textField = new JTextField();
		TopP.add(textField);
		textField.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject:");
		TopP.add(lblSubject);
		
		textField_1 = new JTextField();
		TopP.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel messagesendP = new JPanel();
		frmSendEmail.getContentPane().add(messagesendP, BorderLayout.CENTER);
		messagesendP.setLayout(null);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(25, 13, 69, 20);
		messagesendP.add(lblMessage);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(575, 394, 115, 29);
		messagesendP.add(btnSend);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 31, 667, 347);
		messagesendP.add(scrollPane);
		
		JTextArea messageArea = new JTextArea();
		scrollPane.setViewportView(messageArea);
	}
}
