package Client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.mail.Authenticator;

public class EmailHelper {
	Properties properties;
	Session session;
	JTextField userT;
	JTextField passT;
	JButton loginB;
	EmailMessageWindow messagewindow;

	public EmailHelper() {
		properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true"); // Using TLS
		properties.put("mail.smtp.auth", "true"); // Authenticate
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Using Gmail Account
		properties.put("mail.smtp.port", "587"); // TLS uses port 587

	}

	public void createSession(String username, String password) {
		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public void createLoginWindow() {
		JFrame lgnWindow = new JFrame();
		lgnWindow.setTitle("Email Login");
		lgnWindow.setSize(400, 200);
		lgnWindow.getContentPane().setLayout(new BorderLayout());
		lgnWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel infoP = new JPanel();
		infoP.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;
		JLabel userL = new JLabel("Username:");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		infoP.add(userL, cs);
		userT = new JTextField(15);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		infoP.add(userT, cs);
		JLabel passL = new JLabel("Password:");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		infoP.add(passL, cs);
		passT = new JTextField(15);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		infoP.add(passT, cs);
		lgnWindow.getContentPane().add(infoP, BorderLayout.CENTER);

		JPanel loginBP = new JPanel(new FlowLayout());
		loginB = new JButton("Login");
		loginBP.add(loginB);
		lgnWindow.getContentPane().add(loginBP, BorderLayout.SOUTH);

		lgnWindow.setVisible(true);
	}

	public static void main(String args[]) {
		EmailHelper emailhelper = new EmailHelper();
		emailhelper.createLoginWindow();
	}

	public void createMessage() {
		messagewindow = new EmailMessageWindow();
	}

	public void sendMessage(String from, String to, String sub, String content) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(content);
			Transport.send(message); // Send the Email Message
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
