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
import javax.swing.JScrollPane;

public class EmailMessageWindow {
	 EmailHelper emailhelper;
	 JFrame frmSendEmail;
	 JTextField tfsubject;
	 JButton btnSend;
	 JTextArea messageArea;



	/**
	 * Create the application.
	 */
	public EmailMessageWindow(EmailHelper emailhelper) {
		this.emailhelper = emailhelper;
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


		JLabel lblSubject = new JLabel("Subject:");
		TopP.add(lblSubject);

		tfsubject = new JTextField();
		TopP.add(tfsubject);
		tfsubject.setColumns(10);

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

		messageArea = new JTextArea();
		scrollPane.setViewportView(messageArea);
	}
}
