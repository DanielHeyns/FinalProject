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

public class EmailHelper {
	Properties properties;
	Session session;
	JTextField userT;
	JTextField passT;
	JButton loginB;
	EmailMessageWindow messagewindow;
	String email;

	public EmailHelper() {
		properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true"); // Using TLS
		properties.put("mail.smtp.auth", "true"); // Authenticate
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Using Gmail Account
		properties.put("mail.smtp.port", "587"); // TLS uses port 587

	}

	/**
	 * creation of the actual session, this will authenticate the password and
	 * username
	 *
	 * @param username
	 *            is the email of the user
	 * @param password
	 *            password to be entered by the user
	 */
	public void createSession(String username, String password) {
		email = username;
		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	/**
	 * creates the window to login and gain the info needed to create the session
	 */
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

	/**
	 * calls the email message window, where the user can submit the subject and
	 * message
	 */
	public void createMessage() {
		messagewindow = new EmailMessageWindow();
	}

	/**
	 * the actual procedure of sending the message
	 *
	 * @param to
	 *            is a string containing all desired targets for email in the format
	 *            "email@gmail.com;nextemail@gmail.com", can append as many emails
	 *            as needed separated by ";"
	 * @param sub
	 *            the subject of the message taken from the EmailMessageWindow
	 * @param content
	 *            the actual message desired to be sent, taken from the
	 *            EmailMessageWindow
	 */
	public void sendMessage(String to, String sub, String content) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			String toarray[] = to.split(";");
			for (int i = 0; i < toarray.length; i++) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(toarray[i]));
			}
			message.setSubject(sub);
			message.setText(content);
			Transport.send(message); // Send the Email Message
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
