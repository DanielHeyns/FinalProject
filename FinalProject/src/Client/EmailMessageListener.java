package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailMessageListener implements ActionListener {
	private EmailMessageWindow msgwindow;

	public EmailMessageListener(EmailMessageWindow msgwindow) {
		this.msgwindow = msgwindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == msgwindow.btnSend) {

		}
	}

}
