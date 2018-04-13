package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class EmailMessageWindow {

	 JFrame frmSendEmail;
	 JTextField tfsubject;
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
		TopP.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
<<<<<<< HEAD


		JLabel lblSubject = new JLabel("Subject:");
		TopP.add(lblSubject);

		tfsubject = new JTextField();
		TopP.add(tfsubject);
		tfsubject.setColumns(10);

=======
		
		
		JLabel lblSubject = new JLabel("Subject:");
		TopP.add(lblSubject);
		
		tfsubject = new JTextField();
		TopP.add(tfsubject);
		tfsubject.setColumns(10);
		
>>>>>>> 32a58c261e7bdde756ebcf283c30e47c6e707f3e
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
