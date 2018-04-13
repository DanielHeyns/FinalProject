<<<<<<< HEAD
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailMessageListener implements ActionListener {
	private EmailMessageWindow msgwindow;
	EmailHelper emailhelper;

	public EmailMessageListener(EmailMessageWindow msgwindow, e) {
		this.msgwindow = msgwindow;
		emailhelper = e;
	}

	/**
	 * this should call sendMessage in the EmailHelper class
	 */
	public void actionPerformed(ActionEvent e) {
		//(String sub, String content)
		if (e.getSource() == msgwindow.btnSend) {
			emailhelper.sendMessage(msgwindow.tfsubject.getText(),msgwindow.messageArea.getText());
			msgwindow.frmSendEmail.setVisible(true);
		}
	}

}
=======
package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailMessageListener implements ActionListener {
	private EmailMessageWindow msgwindow;

	public EmailMessageListener(EmailMessageWindow msgwindow) {
		this.msgwindow = msgwindow;
	}

	/**
	 * this should call sendMessage in the EmailHelper class
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == msgwindow.btnSend) {

		}
	}

}
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
