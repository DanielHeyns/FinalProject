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
